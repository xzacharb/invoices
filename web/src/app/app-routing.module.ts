import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';
import { RulesListComponent } from './components/rules-list/rules-list.component';
import { CitiesListComponent } from './components/cities-list/cities-list.component';
import { StatisticsComponent } from './components/statistics/statistics.component';

const routes: Routes = [
  { path: 'invoices/:ruleName/:city', component: InvoiceListComponent },
  { path: 'rules/:id', component: RulesListComponent },
  { path: 'cities', component: CitiesListComponent },
  { path: 'statistics', component: StatisticsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }