import { Component, OnInit } from '@angular/core';
import { City } from '../../objects/city';
import { InvoiceService } from '../../services/invoice-service.service';

@Component({
  selector: 'app-cities-list',
  templateUrl: './cities-list.component.html',
  styleUrls: ['./cities-list.component.css']
})
export class CitiesListComponent implements OnInit {

  cities: City[];
  constructor(private invoiceService: InvoiceService) {
  }

  ngOnInit() {
	  this.invoiceService.findAllCities().subscribe(data => {
      this.cities = data;
	  
  }

}
