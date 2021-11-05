import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';
import { RulesListComponent } from './components/rules-list/rules-list.component';
import { CitiesListComponent } from './components/cities-list/cities-list.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { ProcesesComponent } from './components/proceses/proceses.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: 'companies/:ruleName/:city', component: CompaniesComponent },
  { path: 'invoices/:ruleName/:city/:company', component: InvoiceListComponent },
  { path: 'rules/:id', component: RulesListComponent },
  { path: 'cities', component: CitiesListComponent },
  { path: 'statistics', component: StatisticsComponent },
  { path: '', component: HomeComponent },
  { path: 'processes', component: ProcesesComponent },
  { path: 'processes/:shortName', component: ProcesesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }