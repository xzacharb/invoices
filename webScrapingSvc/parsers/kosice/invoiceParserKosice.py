# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 09:54:49 2021

@author: xzacharb
"""
import requests
import pandas as pd
from bs4 import BeautifulSoup
from selenium import webdriver

URLKosice = 'https://www.zverejnenie.esluzbykosice.sk/Faktura/Index/17270700'
#kosicePage = requests.get(URLKosice)
#kosiceSoup = BeautifulSoup(kosicePage.content, "html.parser")


def get_value():
    driver = webdriver.Chrome()
    driver.get(URLKosice)
    text_field = driver.driver.find_element_by_id('indexGridFooter').find_elements_by_class_name('input-group-btn')
    print(text_field)
   # while driver.find_element_by_id('texto_cpf').text == 'Gerando...':
    #    continue
   # val = driver.find_element_by_id('texto_cpf').text
   # driver.quit()
  #  return val

print(get_value())

def nextPage():
    driver = webdriver.Chrome()
    driver.get(URLKosice)
   # driver.find_element_by_id('indexGridFooter').find_elements_by_class_name('input-group-btn')
    text_field = driver.find_element_by_id('indexGridFooter').find_elements_by_class_name('input-group-btn')
    print(text_field)
    text = wait(driver, 10).until(lambda driver: not text_field.text == 'Gerando...' and text_field.text)
    return text

print(nextPage())

def getPagesNumber():
    pageNumberInput = kosiceSoup.find(id="currentPageNumber")
    currentPageNumbers = pageNumberInput["data-rule-range"][1:-1].split(",")
    currentPageNumbers = list(map(int,currentPageNumbers))
    return currentPageNumbers

def createPageInvoiceDataFrame():
    table = kosiceSoup.find(id='gridData').find('table')
    tableRows = table.find_all('tr')
    res = []
    for tr in tableRows:
        td = tr.find_all('td')
        row = [tr.text.strip() for tr in td if tr.text.strip()]
        
        if row:
            res.append(row)
    
    
    df = pd.DataFrame(res, columns=["interne_cislo","datum_prijatia","ico","dodavatel","adresa","predmet","suma","datum_zverejnenia","zverejnil"])
    return df


print(getPagesNumber())
print(createPageInvoiceDataFrame())