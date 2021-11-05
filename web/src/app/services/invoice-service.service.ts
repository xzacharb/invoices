import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Invoice } from '../objects/invoice';
import { Rule } from '../objects/rule';
import { InfoData } from '../objects/infoData';
import { Person } from '../objects/person';
import { Proces } from '../objects/proces';
import { Company } from '../objects/company';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class InvoiceService {

  private inveicesCities: string;
  private inveicesRules: string;
  private inveicesBase: string;
  private processes: string;

  constructor(private http: HttpClient) {
    this.inveicesBase = 'http://localhost:8080/invoices/';
    this.processes = 'http://localhost:8080/processes/';
    this.inveicesCities = this.inveicesBase+"cities";
    this.inveicesRules = this.inveicesBase+"rules/";
  }

  public findCityRules(city:string): Observable<Rule[]> {
    return this.http.get<Rule[]>(this.inveicesRules+city);
  }
  
  public findCompanyById(companyId:number): Observable<Company> {
    return this.http.get<Company>(this.inveicesBase+"company/"+companyId);
  }
  public findCompanyPersons(companyId:number): Observable<Person[]> {
    return this.http.get<Person[]>(this.inveicesBase+"company/people/"+companyId);
  }
  public findPersonById(personId:number): Observable<Person> {
    return this.http.get<Person>(this.inveicesBase+"people/"+personId);
  }

  public findCityInvoices(ruleName:string, city:string, companyId:string): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.inveicesBase+city+"/"+ruleName+"/"+companyId);
  }
  public findCompanyInfo(ruleName:string, city:string): Observable<InfoData[]> {
    return this.http.get<InfoData[]>(this.inveicesRules+city+"/"+ruleName);
  }
  
  public findAllAlertCities(): Observable<InfoData[]> {
    return this.http.get<InfoData[]>(this.inveicesBase+"cities/alert");
  }
  public findAllCities(): Observable<InfoData[]> {
    return this.http.get<InfoData[]>(this.inveicesBase+"cities");
  }
  public findAllProcesses(): Observable<Proces[]> {
    return this.http.get<Proces[]>(this.processes+"history");
  }
  
  public runEvaluationProcess(city:string): Observable<Proces[]> {
	  console.log(this.processes+"evaluation/"+city);
    return this.http.get<Proces[]>(this.processes+"evaluation/"+city);
  }
  public runDetectionProcess(city:string): Observable<Proces[]> {
	  console.log(this.processes+"detection/"+city);
    return this.http.get<Proces[]>(this.processes+"detection/"+city);
  }
 

}
