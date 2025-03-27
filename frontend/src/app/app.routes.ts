import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ClientDashboardPageComponent } from './pages/client-dashboard-page/client-dashboard-page.component';
import { AdminDashboardPageComponent } from './pages/admin-dashboard-page/admin-dashboard-page.component';

import { authGuard } from './guards/authGuard';
import { roleGuard } from './guards/roleGuard';
import { ProfessionalDashboardPageComponent } from './pages/professional-dashboard-page/professional-dashboard-page.component';

export const routes: Routes = [
  // 📂 Rotte pubbliche
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'dashboard test', component: AdminDashboardPageComponent },
  { path: 'dashboard professional test', component: ProfessionalDashboardPageComponent },

  // 🔒 Area Clienti
  {
    path: 'client',
    canActivate: [authGuard, roleGuard],
    data: { roles: ['ROLE_CLIENT'] },
    children: [
      { path: '', component: ClientDashboardPageComponent }
    ]
  },

  // 🔒 Area Admin
  {
    path: 'admin-dashboard',
    component: AdminDashboardPageComponent,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['ROLE_ADMIN'] }
  },

  // 🌐 Fallback
  { path: '**', redirectTo: '/home' }
];
