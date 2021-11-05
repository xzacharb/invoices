import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';
import { InvoiceService } from './services/invoice-service.service';
import { CitiesListComponent } from './components/cities-list/cities-list.component';
import { RulesListComponent } from './components/rules-list/rules-list.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ProcesesComponent } from './components/proceses/proceses.component';
import { HomeComponent } from './components/home/home.component';
import { CompaniesComponent } from './components/companies/companies.component';

@NgModule({
  declarations: [
    AppComponent,
    InvoiceListComponent,
    CitiesListComponent,
    RulesListComponent,
    StatisticsComponent,
    ProcesesComponent,
    HomeComponent,
    CompaniesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
	ReactiveFormsModule
  ],
  providers: [InvoiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }