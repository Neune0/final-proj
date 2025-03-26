import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientRoutingModule } from './client-routing.module';  // Importa ClientRoutingModule
import { ServiceCardComponent } from './components/service-card/service-card.component';
import { HomeComponent } from './pages/home/home.component';  // Importa HomeComponent
import { ServizioComponent } from './servizio/servizio.component';  // Importa ServizioComponent

@NgModule({
  declarations: [
    ServiceCardComponent,
    HomeComponent,
    ServizioComponent  // Dichiara ServizioComponent qui
  ],
  imports: [
    CommonModule,
    ClientRoutingModule  // Importa ClientRoutingModule
  ]
})
export class ClientModule { }
