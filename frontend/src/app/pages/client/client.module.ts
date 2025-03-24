import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ClientRoutingModule } from './client-routing.module';

// Componenti del lato client
import { ClientComponent } from './client.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';

// Navbar condivisa
import { NavbarComponent } from '../../shared/navbar/navbar.component';

@NgModule({
  declarations: [
    ClientComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ClientRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ClientModule { }
