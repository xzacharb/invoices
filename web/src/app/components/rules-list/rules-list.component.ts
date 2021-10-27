import { Component, OnInit } from '@angular/core';
import { Rule } from '../../objects/rule';
import { InvoiceService } from '../../services/invoice-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-rules-list',
  templateUrl: './rules-list.component.html',
  styleUrls: ['./rules-list.component.css']
})
export class RulesListComponent implements OnInit {
  rules: Rule[];
  constructor(private invoiceService: InvoiceService, private route: ActivatedRoute) { }

  ngOnInit() {
	  this.invoiceService.findCityRules(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      this.rules = data;
    });
  }

}
