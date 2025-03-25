import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './pages/home/home.component';
import { ClientRoutingModule } from './client-routing.module'; // <--- aggiungi questo

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule // <--- aggiungi qui
  ]
})
export class ClientModule { }
