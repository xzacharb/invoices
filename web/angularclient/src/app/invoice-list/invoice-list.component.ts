import { Component, OnInit } from '@angular/core';
import { Invoice } from '../invoice';
import { InvoiceService } from '../invoice-service.service';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {

  invoices: Invoice[];

  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit() {
    this.invoiceService.findAll().subscribe(data => {
      this.invoices = data;
    });
  }

}
