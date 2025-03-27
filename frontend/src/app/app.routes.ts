import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
// import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { authGuard } from './guards/authGuard';
import { roleGuard } from './guards/roleGuard';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { ClientDashboardPageComponent } from './pages/client-dashboard-page/client-dashboard-page.component';
// import { UnauthorizedComponent } from './shared/components/unauthorized/unauthorized.component';

export const routes: Routes = [
    // Route pubbliche
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'register', component: RegisterPageComponent},
    {path: 'home', component: HomePageComponent},
    {path: 'login', component: LoginPageComponent},
    // {path: 'unauthorized', component: UnauthorizedComponent},
    
    // Route protette per utenti autenticati
    // {
    //     path: 'profile',
    //     component: ProfileComponent,
    //     canActivate: [authGuard]
    // },
    
    // Route protette per clienti
    {
        path: 'client',
        canActivate: [authGuard, roleGuard],
        data: { roles: ['ROLE_CLIENT'] },
        children: [
            {path: '', component: ClientDashboardPageComponent},
            // {path: 'services', component: ClientServicesComponent}
        ]
    },
    
    // Route protette per professionisti
    // {
    //     path: 'professional',
    //     canActivate: [authGuard, roleGuard],
    //     data: { roles: ['ROLE_PROFESSIONAL'] },
    //     children: [
    //         {path: '', component: ProfessionalDashboardComponent},
    //         {path: 'services', component: ManageServicesComponent}
    //     ]
    // },
    
    // Route protette per admin
    // {
    //     path: 'admin',
    //     canActivate: [authGuard, roleGuard],
    //     data: { roles: ['ROLE_ADMIN'] },
    //     children: [
    //         {path: '', component: AdminDashboardComponent},
    //         {path: 'users', component: UsersManagementComponent}
    //     ]
    // },
    
    // Fallback
    // {path: '**', redirectTo: '/home'}
];