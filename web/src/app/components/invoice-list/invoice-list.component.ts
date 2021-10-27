import { Component, OnInit } from '@angular/core';
import { Invoice } from '../../objects/invoice';
import { InvoiceService } from '../../services/invoice-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {

  invoices: Invoice[];

  constructor(private invoiceService: InvoiceService, private route: ActivatedRoute) {
  }

  ngOnInit() {
	  this.invoiceService.findCityInvoices(this.route.snapshot.paramMap.get('ruleName'),this.route.snapshot.paramMap.get('city')).subscribe(data => {
      this.invoices = data;
    });
  }

}
