import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InvoiceListComponent } from './components/invoice-list/invoice-list.component';
import { CitiesListComponent } from './components/cities-list/cities-list.component';

const routes: Routes = [
  { path: 'invoices', component: InvoiceListComponent },
  { path: 'invoices/:id', component: InvoiceListComponent },
  { path: 'cities', component: CitiesListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }