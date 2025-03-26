import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Rimuovi il `ServizioComponent` dall'importazione
// Invece, lascia che venga gestito da ClientModule tramite lazy loading

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('./features/client/client.module').then(m => m.ClientModule)
  },
  {
    path: 'professional',
    loadChildren: () =>
      import('./features/professional/professional.module').then(m => m.ProfessionalModule)
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./features/admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then(m => m.AuthModule)
  },
  // Le rotte per i servizi saranno gestite all'interno di ClientRoutingModule
  {
    path: 'servizi',
    loadChildren: () =>
      import('./features/client/client.module').then(m => m.ClientModule) // Carica il modulo ClientModule, che gestirà le rotte dei servizi
  },
  {
    path: 'professionisti', 
    loadChildren: () => 
      import('./features/professional/professional.module').then(m => m.ProfessionalModule)
  },
  {
    path: 'docenti', 
    loadChildren: () => 
      import('./features/admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
