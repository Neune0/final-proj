import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessionalRoutingModule } from './professional-routing.module';
import { ProfessionalComponent } from './professional.component';
import { LoginComponent } from '../client/login/login.component'; // Corretto

import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    ProfessionalComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    ProfessionalRoutingModule
  ]
})
export class ProfessionalModule { }
