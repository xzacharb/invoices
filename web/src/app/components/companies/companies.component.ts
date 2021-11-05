import { Component, OnInit } from '@angular/core';
import { InfoData } from '../../objects/infoData';
import { ActivatedRoute } from '@angular/router';
import { InvoiceService } from '../../services/invoice-service.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {
  companies: InfoData[];
  ruleNameShort:string;
  cityShort:string;
  
  constructor(private invoiceService: InvoiceService, private route: ActivatedRoute) { }

  ngOnInit() {
	  this.ruleNameShort = this.route.snapshot.paramMap.get('ruleName');
	  this.cityShort = this.route.snapshot.paramMap.get('city');
	  this.invoiceService.findCompanyInfo(this.ruleNameShort,this.cityShort).subscribe(data => {
      this.companies = data;
    });
  }

}
