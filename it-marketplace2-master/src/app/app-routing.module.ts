import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'professional', loadChildren: () => import('./features/professional/professional.module').then(m => m.ProfessionalModule) }, { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule) }, { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
