import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/AuthService';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  if (authService.isAuthenticated()) {
    return true;
  }
  
  // Utente non autenticato, reindirizza al login
  return router.createUrlTree(['/login'], { 
    queryParams: { returnUrl: state.url } 
  });
};