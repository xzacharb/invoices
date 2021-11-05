import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { InvoiceService } from "../../services/invoice-service.service";
import { InfoData } from "../../objects/infoData";
import { Proces } from "../../objects/proces";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-proceses",
  templateUrl: "./proceses.component.html",
  styleUrls: ["./proceses.component.css"],
})
export class ProcesesComponent implements OnInit {
  processForm: any;
  cities: InfoData[];
  processes: Proces[];
  runProceessFrom: Boolean = false;
  showProcesses: Boolean = false;
  shortName: string;

  constructor(
    private invoiceService: InvoiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.shortName = this.route.snapshot.paramMap.get("shortName");
    if (this.shortName) {
      if (this.shortName === "run") {
        this.runProceessFrom = true;
      } else if (this.shortName === "history") {
        this.showProcesses = true;
      }
    }
    this.invoiceService.findAllCities().subscribe((data) => {
      this.cities = data;
    });
    this.processForm = new FormGroup({
      name: new FormControl(null),
      city: new FormControl(null),
    });
    this.invoiceService.findAllProcesses().subscribe((data) => {
      this.processes = data;
    });
  }
  submitData() {
    let city: string = this.processForm.value.city;

    if (this.processForm.value.name == "detection") {
      this.invoiceService.runDetectionProcess(city).subscribe();
    } else if (this.processForm.value.name == "evaluation") {
      this.invoiceService.runEvaluationProcess(city).subscribe();
    }

    this.showProcesses = true;
    this.runProceessFrom = false;
    this.invoiceService.findAllProcesses().subscribe((data) => {
      this.processes = data;
    });
  }
}
