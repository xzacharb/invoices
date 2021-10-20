# -*- coding: utf-8 -*-
"""
Created on Wed Oct 20 09:48:39 2021

@author: xzacharb
"""
from flask import Flask
from parsers.kosice import invoiceParserKosice



app = Flask(__name__)


@app.route('/')
def home():
    result = invoiceParserKosice.runProces()

    return result

if __name__ == '__main__':
    app.run(debug=False)


