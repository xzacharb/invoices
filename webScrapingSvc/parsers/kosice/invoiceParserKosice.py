# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 09:54:49 2021

@author: xzacharb
"""

import time
import pandas as pd
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from time import sleep
from json import dumps
from kafka import KafkaProducer
import json
import unicodedata
from dateutil.parser import parse
from datetime import datetime

    
    
def initDriver(url):
    print('init driver')
    driver = webdriver.Chrome('./chromedriver.exe') 
    options = webdriver.ChromeOptions()
    options.headless = True
    options.add_argument("window-size=1920x1080")
    options.add_argument('--no-sandbox')
    options.add_argument('--disable-gpu')
    # options.add_argument('--disable-dev-shm-usage') # Not used but can be an option
    driver = webdriver.Chrome(options=options)
    driver.get(url)
    return driver
    
def gotoNextPage(driver,pageNumber):
    print('go to next page')
    inputElement = driver.find_element_by_id('currentPageNumber')
    inputElement.send_keys(pageNumber)
    parent = inputElement.find_element_by_xpath("..")
    nextPageButton = parent.find_elements_by_xpath("//button[@title='Prechod na požadovanú stránku']")[0]
    nextPageButton.click()
    time.sleep(1)
    return driver
def isDate(string, fuzzy=False):
    try: 
        parse(string, fuzzy=fuzzy)
        return True

    except ValueError:
        return False
    
def getNaxPageNumber(driver):
    kosiceSoup = BeautifulSoup(driver)
    pageNumberInput = kosiceSoup.find(id="currentPageNumber")
    currentPageNumbers = pageNumberInput["data-rule-range"][1:-1].split(",")
    currentPageNumbers = list(map(int,currentPageNumbers))
    return currentPageNumbers[1]

def addDataToKafka(data):
    producer = KafkaProducer(bootstrap_servers=['192.168.99.100:9092'],
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))
    producer.send('invoices', json.loads(pd.Series(data).to_json()))
    
        

def getTableData(h2):
    print('getting table data')
    h2.click()
    cardHeader = h2.find_element_by_xpath("..")
    expandButton = cardHeader.find_element_by_xpath("//button")
    expandButton.click() 
    time.sleep(1)
    
    cardSoup = BeautifulSoup(cardHeader.parent.page_source)
    tableRows = cardSoup.find_all('tr')
    res = []
    for tr in tableRows:
        td = tr.find_all('td')
        row = [tr.text.strip() for tr in td if tr.text.strip()]
        
        if row:
            res.append(row)
    return res

def getContractorInfo(driver,transparexURL):
    print('contractor info')
    contractorDriver = driver.find_elements_by_xpath("//label[text()='IČO:']")
    if contractorDriver is not None:
        if contractorDriver :
            contractorDriver = contractorDriver[0]
            icoDiv = contractorDriver.find_elements_by_xpath("..")[0]
            cardDiv = icoDiv.find_elements_by_xpath("..")[0]
            nameH2Div = cardDiv.find_elements_by_xpath("//h2")[0]
            nameDiv = nameH2Div.find_elements_by_xpath("..")[0]
            
            dateCreatedStr = driver.find_elements_by_xpath("//label[text()='Dátum vzniku:']")[0].find_elements_by_xpath("..")[0].find_element_by_tag_name("span").text
            address = nameDiv.find_element_by_tag_name("span").text
            name= nameH2Div.text
            ico = icoDiv.find_element_by_tag_name("span").text
            
            dateCreatedObj = datetime.strptime(dateCreatedStr, '%d.%m.%Y')
            return {'name':name,'address':address,'description':'','source':transparexURL,'ico':ico,'date_created':dateCreatedObj,'legalFormId':'empty'}
    
        
    else:
        data = {'name':'','address':'','description':'','source':'','ico':'','date_created':'','legalFormId':'empty'}
    
        
    
def getPeople(driver,urlTransparex,contractorObj,cityName):
    print('get people')
    statutariDriver = driver.find_elements_by_xpath("//h2[@title='Štatutári']")
    statutariDriver = statutariDriver[0]
    spolocniciDriver = driver.find_elements_by_xpath("//h2[@title='Spoločníci']")
    spolocniciDriver = spolocniciDriver[0]
    
    persons = []
    
    ludia = getTableData(statutariDriver)
    spolocnici = getTableData(spolocniciDriver)
    if spolocnici:
        if isinstance(spolocnici[0], list):
            for spol in spolocnici:
                ludia.append(spol)
    for spol in ludia:
        role=spol[1]
        if isDate(spol[-1]):
            dateCreated = datetime.strptime(spol[-1], '%d.%m.%Y')
            print('statutar: '+spol[0])
            print(dateCreated)
            persons.append({'name':spol[0],'middleName':'','sureName':'','address':spol[2],'source':urlTransparex,'date_start':dateCreated,'managementType':'statutar','role':role,'cityObjDao':cityName})
            
        elif len(spol) > 2 and not isinstance(spol[0], list):
            print('spolocnik: ')
            address = ''
            if len(spol) > 3:
                address = spol[3]
            else:
                address = spol[2]
            persons.append( {'name':spol[0],'middleName':'','sureName':'','address':address,'source':urlTransparex,'managementType':'spolocnik','role':role,'cityObjDao':cityName})
            
    return persons
    
       

def createPageInvoiceDataFrame(driverKosice,URLKosice,cityShortCut, urlTransparex):
    print('create invoice data')
    kosicePage = driverKosice.page_source
    kosiceSoup = BeautifulSoup(kosicePage)
    table = kosiceSoup.find(id='gridData').find('table')
    tableRows = table.find_all('tr')
    for tr in tableRows:
        td = tr.find_all('td')
        row = [tr.text.strip() for tr in td if tr.text.strip()]
        
        if row:
            print(row)
            if len(row) < 9: #no address defined
                cityName = unicodedata.normalize('NFKD', row[7]).encode('ascii', 'ignore').decode()
                address = ''
                priceCol = unicodedata.normalize('NFKD', row[5]).encode('ascii', 'ignore').decode()
                subject = unicodedata.normalize('NFKD', row[4]).encode('ascii', 'ignore').decode() 
                datePublishedCol = unicodedata.normalize('NFKD', row[6]).encode('ascii', 'ignore').decode()

            else:
                cityName = unicodedata.normalize('NFKD', row[8]).encode('ascii', 'ignore').decode()
                priceCol = unicodedata.normalize('NFKD', row[6]).encode('ascii', 'ignore').decode()
                subject = unicodedata.normalize('NFKD', row[5]).encode('ascii', 'ignore').decode()
                address = unicodedata.normalize('NFKD', row[4]).encode('ascii', 'ignore').decode()
                datePublishedCol = unicodedata.normalize('NFKD', row[7]).encode('ascii', 'ignore').decode()

            dateSignedCol = unicodedata.normalize('NFKD', row[1]).encode('ascii', 'ignore').decode()
            ico = unicodedata.normalize('NFKD', row[2]).encode('ascii', 'ignore').decode()
            if not ico:
                continue
            price = float(priceCol.replace(' ', '').replace(',', '.'))
            dateSigned = datetime.strptime(dateSignedCol, '%d.%m.%Y')
            datePublished = datetime.strptime(datePublishedCol, '%d.%m.%Y')
            descritpion = unicodedata.normalize('NFKD', row[0]).encode('ascii', 'ignore').decode()
            
            city = {'city_short':cityShortCut, 'city_name':cityName}
            
            
            URLTransparexPerIco = 'https://www.transparex.sk/organization/'+ico+'/profile'
            print('transparexUrl')
            print(URLTransparexPerIco)
            driverTransparex = initDriver(URLTransparexPerIco)
            
            contractor = getContractorInfo(driverTransparex,URLTransparexPerIco)
            if contractor and contractor is not None:
                if contractor["ico"]:
                    people = getPeople(driverTransparex,URLTransparexPerIco,contractor,city["city_short"])
            else:
                contractorName = unicodedata.normalize('NFKD', row[3]).encode('ascii', 'ignore').decode()
                contractor = {'name':contractorName,'address':address,'description':'zahranicna spolocnost','source':URLKosice,'ico':ico,'date_created':'','legalFormId':'empty'}
                people = []
                
            invoiceData = {'price': price, 'subject':subject,'description':descritpion,'comment':'','date_signed':dateSigned,'date_published':datePublished,'source':URLKosice,'city':city, 'contractor':contractor,'personList':people}    
            driverTransparex.close()
            addDataToKafka(invoiceData)
            
            
def runProces(kosiceShortCut):
    URLTransparex = 'https://www.transparex.sk/organization/'
    URLKosice = 'https://www.zverejnenie.esluzbykosice.sk/Faktura/Index/17270700'
    columnNames = ["interne_cislo","datum_prijatia","ico","dodavatel","adresa","predmet","suma","datum_zverejnenia","zverejnil"]
    invoicesDF = pd.DataFrame(columns = columnNames)
    startPage =1
    driverKosice = initDriver(URLKosice)
    maxPageNumber=getNaxPageNumber(driverKosice.page_source)
    print('run processes')
    
    for pageNumber in range(startPage,maxPageNumber):        
        driverKosice = gotoNextPage(driverKosice,pageNumber)
        time.sleep(2)
        createPageInvoiceDataFrame(driverKosice,URLKosice,kosiceShortCut, URLTransparex)
        
    return {"success":"success"}

def getContractor():
    URLTransparex = 'https://www.transparex.sk/organization/'+'31698301/profile'
    driverTransparex = initDriver(URLTransparex)
    getPeople(driverTransparex)
