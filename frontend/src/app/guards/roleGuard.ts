import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/AuthService';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  // Prima verifica se l'utente è autenticato
  if (!authService.isAuthenticated()) {
    return router.createUrlTree(['/login'], {
      queryParams: { returnUrl: state.url }
    });
  }
  
  // Poi verifica il ruolo
  const requiredRoles = route.data['roles'] as string[];
  
  if (!requiredRoles || requiredRoles.length === 0) {
    console.warn('No roles specified for roleGuard');
    return true;
  }
  
  if (authService.hasRole(requiredRoles)) {
    return true;
  }
  
  // Utente non ha i permessi, reindirizza alla pagina di accesso negato
  return router.createUrlTree(['/unauthorized']);
};