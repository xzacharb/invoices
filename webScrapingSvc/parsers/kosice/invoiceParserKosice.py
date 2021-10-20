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



def initDriver(url):
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
    inputElement = driver.find_element_by_id('currentPageNumber')
    inputElement.send_keys(pageNumber)
    parent = inputElement.find_element_by_xpath("..")
    nextPageButton = parent.find_elements_by_xpath("//button[@title='Prechod na požadovanú stránku']")[0]
    nextPageButton.click()
    time.sleep(3)
    return driver

def getNaxPageNumber(driver):
    kosiceSoup = BeautifulSoup(driver)
    pageNumberInput = kosiceSoup.find(id="currentPageNumber")
    currentPageNumbers = pageNumberInput["data-rule-range"][1:-1].split(",")
    currentPageNumbers = list(map(int,currentPageNumbers))
    return currentPageNumbers[1]

def createPageInvoiceDataFrame(driver,invoice):
    kosiceSoup = BeautifulSoup(driver)
    table = kosiceSoup.find(id='gridData').find('table')
    tableRows = table.find_all('tr')
    res = []
    for tr in tableRows:
        td = tr.find_all('td')
        row = [tr.text.strip() for tr in td if tr.text.strip()]
        
        if row:
            a_series = pd.Series(row, index = invoice.columns)
            invoice = invoice.append(a_series, ignore_index=True)
        
    return invoice
def runProces():
    URLKosice = 'https://www.zverejnenie.esluzbykosice.sk/Faktura/Index/17270700'
    columnNames = ["interne_cislo","datum_prijatia","ico","dodavatel","adresa","predmet","suma","datum_zverejnenia","zverejnil"]
    invoicesDF = pd.DataFrame(columns = columnNames)
    
    driver = initDriver(URLKosice)
    maxPageNumber=5#getNaxPageNumber(driver.page_source)
    
    for pageNumber in range(1,maxPageNumber):
        driver = gotoNextPage(driver,maxPageNumber)
        invoicesDF = createPageInvoiceDataFrame(driver.page_source,invoicesDF)
    
    print(invoicesDF)
    return invoicesDF
