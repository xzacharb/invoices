import { Component, OnInit } from "@angular/core";
import { InfoData } from "../../objects/infoData";
import { InvoiceService } from "../../services/invoice-service.service";

@Component({
  selector: "app-cities-list",
  templateUrl: "./cities-list.component.html",
  styleUrls: ["./cities-list.component.css"],
})
export class CitiesListComponent implements OnInit {
  cities: InfoData[];
  constructor(private invoiceService: InvoiceService) {}

  ngOnInit() {
    this.invoiceService.findAllAlertCities().subscribe((data) => {
      this.cities = data;
    });
  }
}
