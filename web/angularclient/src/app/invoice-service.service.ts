import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Invoice } from './invoice';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private inveicesByIdURL: string;
  private inveicesKosice: string;
  private inveicesSave: string;

  constructor(private http: HttpClient) {
    this.inveicesByIdURL = 'http://localhost:8080/invoices/city/{id}';
    this.inveicesKosice = 'http://localhost:8080/invoices/sampleCity1';
    this.inveicesSave = 'http://localhost:8080/kafka/publish';
  }

  public findAll(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.inveicesKosice);
  }

  public save(invoice: Invoice) {
    return this.http.post<Invoice>(this.inveicesSave, invoice);
  }

}
