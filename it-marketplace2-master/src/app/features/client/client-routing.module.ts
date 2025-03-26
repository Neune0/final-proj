import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';  // Importa HomeComponent
import { ServizioComponent } from './servizio/servizio.component';  // Importa ServizioComponent

const routes: Routes = [
  { path: '', component: HomeComponent },  // Rotta per la homepage
  { path: 'servizi/sviluppo-frontend', component: ServizioComponent, data: { title: 'Sviluppo Frontend' } },
  { path: 'sviluppo-backend', component: ServizioComponent, data: { title: 'Sviluppo Backend' } },
  { path: 'gestione-database', component: ServizioComponent, data: { title: 'Gestione Database' } },
  { path: 'marketing', component: ServizioComponent, data: { title: 'Marketing' } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
