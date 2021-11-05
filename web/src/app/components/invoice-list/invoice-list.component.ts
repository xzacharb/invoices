import { Component, OnInit } from "@angular/core";
import { Invoice } from "../../objects/invoice";
import { Person } from "../../objects/person";
import { Company } from "../../objects/company";
import { InvoiceService } from "../../services/invoice-service.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-invoice-list",
  templateUrl: "./invoice-list.component.html",
  styleUrls: ["./invoice-list.component.css"],
})
export class InvoiceListComponent implements OnInit {
  invoices: Invoice[];
  persons: Person[];
  company: Company;

  showContractorId: number = -1;
  showPersons: Boolean = false;
  showPersonDetail: Boolean = false;
  personDetail: Person;
  contractorId: string;
  constructor(
    private invoiceService: InvoiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.contractorId = this.route.snapshot.paramMap.get("company");
    this.invoiceService
      .findCityInvoices(
        this.route.snapshot.paramMap.get("ruleName"),
        this.route.snapshot.paramMap.get("city"),
        this.contractorId
      )
      .subscribe((data) => {
        this.invoices = data;
      });
    this.invoiceService
      .findCompanyById(+this.contractorId)
      .subscribe((data) => {
        this.company = data;
      });
  }
  findPersonById(id: number) {
    this.invoiceService.findPersonById(id).subscribe((data) => {
      this.personDetail = data;
      this.showPersonDetail = true;
    });
  }
  getContractorData(contractorId: number) {
    if (contractorId == this.showContractorId) {
      this.showContractorId = -1;
      this.showPersons = false;
      this.showPersonDetail = false;
    } else {
      this.showContractorId = contractorId;
      this.invoiceService.findCompanyById(contractorId).subscribe((data) => {
        this.company = data;
      });
      this.invoiceService.findCompanyPersons(contractorId).subscribe((data) => {
        this.persons = data;
        if (this.persons.length) {
          this.showPersons = true;
        } else {
          this.showPersons = false;
          this.showPersonDetail = false;
        }
      });
    }
  }
}
