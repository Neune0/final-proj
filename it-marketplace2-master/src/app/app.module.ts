import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module'; // 👈 IMPORT QUI

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule // 👈 AGGIUNGI QUI
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
