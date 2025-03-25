import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [{ path: 'client', loadChildren: () => import('./pages/client/client.module').then(m => m.ClientModule) }, { path: 'admin', loadChildren: () => import('./pages/admin/admin.module').then(m => m.AdminModule) }, { path: 'professional', loadChildren: () => import('./pages/professional/professional.module').then(m => m.ProfessionalModule) }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
