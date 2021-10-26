import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Invoice } from '../objects/invoice';
import { City } from '../objects/city';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private inveicesCities: string;
  private inveicesByIdURL: string;
  private inveicesKosice: string;
  private inveicesSave: string;
  private inveicesBase: string;

  constructor(private http: HttpClient) {
    this.inveicesCities = 'http://localhost:8080/overview';
    this.inveicesByIdURL = 'http://localhost:8080/invoices/city/{:id}';
    this.inveicesKosice = 'http://localhost:8080/invoices/sampleCity1';
    this.inveicesBase = 'http://localhost:8080/invoices/';
    this.inveicesSave = 'http://localhost:8080/kafka/publish';
  }

  public findAll(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.inveicesKosice);
  }
  public findCityInvoices(city:string): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.inveicesBase+city);
  }
  public findAllCities(): Observable<City[]> {
    return this.http.get<City[]>(this.inveicesCities);
  }

  public save(invoice: Invoice) {
    return this.http.post<Invoice>(this.inveicesSave, invoice);
  }

}
