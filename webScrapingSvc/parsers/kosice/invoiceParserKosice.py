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
    
    '''producer = KafkaProducer(bootstrap_servers=['192.168.99.100:9092'],
                         value_serializer=lambda x: 
                         dumps(x).encode('utf-8'))'''
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
    #print(dateCreatedObj)
    #vString name, String address, String description, String source, String ico, Date dateCreated

    data = {'name':name,'address':address,'description':'','source':transparexURL,'ico':ico,'date_created':dateCreatedObj,'legalFormId':'empty'}
    
    return data
    
def getPeople(driver,urlTransparex,contractorObj,cityName):
    print('get people')
    statutariDriver = driver.find_elements_by_xpath("//h2[@title='Štatutári']")
    statutariDriver = statutariDriver[0]
    spolocniciDriver = driver.find_elements_by_xpath("//h2[@title='Spoločníci']")
    spolocniciDriver = spolocniciDriver[0]
    
    persons = []
    
    statutari = getTableData(statutariDriver)
    spolocnici = getTableData(spolocniciDriver)
    if spolocnici:
        if isinstance(spolocnici[0], list):
            for spol in spolocnici:
                statutari.append(spol)
    #String name, String middleName, String sureName, String address, String source, Date dateStart, 
    #Role role, Contractor contractorObjDao, City cityObjDao, ManagementType managementType

    for spol in statutari:
        role=spol[1]
        if isDate(spol[-1]):
            dateCreated = datetime.strptime(spol[-1], '%d.%m.%Y')
            print('statutar: '+spol[0])
            print(dateCreated)
            stat = {'name':spol[0],'middleName':'','sureName':'','address':spol[2],'source':urlTransparex,'date_start':dateCreated,'managementType':'statutar','role':role,'cityObjDao':cityName}
            persons.append(stat)
        else :
            print('spolocnik: '+spol[0])
            dateCreated = datetime.strptime('1.1.1970', '%d.%m.%Y')
            print(dateCreated)
            spol = {'name':spol[0],'middleName':'','sureName':'','address':spol[2],'source':urlTransparex,'managementType':'spolocnik','role':role,'cityObjDao':cityName}
            persons.append(spol)
    return persons
    
       

def createPageInvoiceDataFrame(driverKosice,URLKosice,cityShortCut, urlTransparex):
    print('create invoice data')
    kosicePage = driverKosice.page_source
    kosiceSoup = BeautifulSoup(kosicePage)
    table = kosiceSoup.find(id='gridData').find('table')
    tableRows = table.find_all('tr')
    res = []
    for tr in tableRows:
        td = tr.find_all('td')
        row = [tr.text.strip() for tr in td if tr.text.strip()]
        
        if row:
            print(row)
            res.append(row)
            #int price, String subject, String description, String comment, Date date_signed, Date date_published, String source, City city, Contractor contractor
            priceCol = unicodedata.normalize('NFKD', row[6]).encode('ascii', 'ignore').decode()
            subject = unicodedata.normalize('NFKD', row[5]).encode('ascii', 'ignore').decode()
            dateSignedCol = unicodedata.normalize('NFKD', row[1]).encode('ascii', 'ignore').decode()
            ico = unicodedata.normalize('NFKD', row[2]).encode('ascii', 'ignore').decode()
            if not ico:
                continue
            datePublishedCol = unicodedata.normalize('NFKD', row[7]).encode('ascii', 'ignore').decode()
            cityName = unicodedata.normalize('NFKD', row[8]).encode('ascii', 'ignore').decode()
            price = float(priceCol.replace(' ', '').replace(',', '.'))
            dateSigned = datetime.strptime(dateSignedCol, '%d.%m.%Y')
            datePublished = datetime.strptime(datePublishedCol, '%d.%m.%Y')
            descritpion = 'interne cislo'+unicodedata.normalize('NFKD', row[0]).encode('ascii', 'ignore').decode()
            
            city = {'city_short':cityShortCut, 'city_name':cityName}
            
            
            URLTransparexPerIco = 'https://www.transparex.sk/organization/'+ico+'/profile'
            print('transparexUrl')
            print(URLTransparexPerIco)
            driverTransparex = initDriver(URLTransparexPerIco)
            
            contractor = getContractorInfo(driverTransparex,URLTransparexPerIco)
            people = getPeople(driverTransparex,urlTransparex,contractor,city["city_short"])
            invoiceData = {'price': price, 'subject':subject,'description':descritpion,'comment':'','date_signed':dateSigned,'date_published':datePublished,'source':URLKosice,'city':city, 'contractor':contractor,'personList':people}
            
            driverTransparex.close()
            
            print(invoiceData)
            addDataToKafka(invoiceData)
            break
            
def runProces(kosiceShortCut):
    
    URLTransparex = 'https://www.transparex.sk/organization/'
    URLKosice = 'https://www.zverejnenie.esluzbykosice.sk/Faktura/Index/17270700'
    columnNames = ["interne_cislo","datum_prijatia","ico","dodavatel","adresa","predmet","suma","datum_zverejnenia","zverejnil"]
    invoicesDF = pd.DataFrame(columns = columnNames)
    
    driverKosice = initDriver(URLKosice)
    maxPageNumber=2#getNaxPageNumber(driverKosice.page_source)
    print('run processes')
    for pageNumber in range(1,maxPageNumber):
        time.sleep(2)
        driverKosice = gotoNextPage(driverKosice,maxPageNumber)
        createPageInvoiceDataFrame(driverKosice,URLKosice,kosiceShortCut, URLTransparex)
        
    return {"success":"success"}
def test():
    people = [{'name':'person1', 'address':'address1'}, {'name':'person2', 'address':'address2'}]
    contractor1 = {'name':"cont1", 'people':people}
    contractor2 = {'name':"cont2", 'people':people}
    i1 = {'price':1, 'contractor':contractor1}
    i2 =  {'price':3, 'contactor':contractor2}
    aList = []
    aList.append(i1)
    aList.append(i2)
    jsonStr = json.dumps(i1)
    print(jsonStr)

def getContractor():
    URLTransparex = 'https://www.transparex.sk/organization/'+'31698301/profile'
    driverTransparex = initDriver(URLTransparex)
    getPeople(driverTransparex)
kosiceShortCut="ke"
runProces(kosiceShortCut)
'''cts=[]
ct = {"city_short":"ke","city_name":"kosice"}
cts.append(ct)
cts.append({"city_short":"po","city_name":"presov"})
city = {"price":150.72,"subject":"Servisne prace na bezpecnostnych systemoch na objektoch MsP","description":"202103485","comment":"Kratka 367\/5, 044 11 Zdana","date_signed":"","date_published":"","city":ct,"cities":cts,"id":"","source":"https:\/\/www.zverejnenie.esluzbykosice.sk\/Faktura\/Index\/17270700"}
addDataToKafka(city)'''
#getContractor()