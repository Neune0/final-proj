import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessionalRoutingModule } from './professional-routing.module';
import { ProfessionalComponent } from './professional.component';
import { ProfessionistiComponent } from './professionisti/professionisti.component';


@NgModule({
  declarations: [
    ProfessionalComponent,
    ProfessionistiComponent
  ],
  imports: [
    CommonModule,
    ProfessionalRoutingModule
  ]
})
export class ProfessionalModule { }
