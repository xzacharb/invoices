import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Invoice } from '../objects/invoice';
import { Rule } from '../objects/rule';
import { City } from '../objects/city';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private inveicesCities: string;
  private inveicesRules: string;
  private inveicesBase: string;

  constructor(private http: HttpClient) {
    this.inveicesBase = 'http://localhost:8080/invoices/';
    this.inveicesCities = this.inveicesBase+"cities";
    this.inveicesRules = this.inveicesBase+"rules/";
  }

  public findCityRules(city:string): Observable<Rule[]> {
    return this.http.get<Rule[]>(this.inveicesRules+city);
  }

  public findCityInvoices(ruleName:string, city:string): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.inveicesBase+city+"/"+ruleName);
  }
  public findAllCities(): Observable<City[]> {
    return this.http.get<City[]>(this.inveicesCities);
  }

}
