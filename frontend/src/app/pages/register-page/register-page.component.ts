import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/AuthService';
import { RegisterRequest } from '../../models/Auth';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink,],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css',
})
export class RegisterPageComponent {
  activeTab: 'client' | 'professional' = 'client';

  // Shared fields
  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  email: string = '';

  // Client specific fields
  firstName: string = '';
  lastName: string = '';

  // Professional specific fields
  profession: string = '';
  company: string = '';

  loading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  setActiveTab(tab: 'client' | 'professional'): void {
    this.activeTab = tab;
    this.clearMessages();
  }

  onSubmit(): void {
    this.clearMessages();

    // Validate form
    if (!this.validateForm()) {
      return;
    }

    this.loading = true;

    const registerData: RegisterRequest = {
      username: this.username,
      password: this.password,
      email: this.email,
      firstName: this.activeTab === 'client' ? this.firstName : undefined,
      lastName: this.activeTab === 'client' ? this.lastName : undefined,
      profession:
        this.activeTab === 'professional' ? this.profession : undefined,
      company: this.activeTab === 'professional' ? this.company : undefined,
    };

    if (this.activeTab === 'client') {
      this.registerClient(registerData);
    } else {
      this.registerProfessional(registerData);
    }
  }

  private registerClient(data: RegisterRequest): void {
    this.authService.registerClient(data).subscribe({
      next: (response) => {
        console.log('Registration successful, response:', response);
        this.successMessage = 'Registrazione completata con successo. Ora puoi accedere.';
        this.loading = false;
        
        this.router.navigateByUrl('/login');
        
      },
      error: (error) => {
        this.errorMessage =
          error.message || 'Si è verificato un errore durante la registrazione';
        this.loading = false;
      },
    });
  }

  private registerProfessional(data: RegisterRequest): void {
    this.authService.registerProfessional(data).subscribe({
      next: () => {
        this.successMessage =
          'Registrazione completata con successo. Ora puoi accedere.';
        this.loading = false;
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        this.errorMessage =
          error.message || 'Si è verificato un errore durante la registrazione';
        this.loading = false;
      },
    });
  }

  private validateForm(): boolean {
    // Required fields validation
    if (
      !this.username ||
      !this.password ||
      !this.confirmPassword ||
      !this.email
    ) {
      this.errorMessage = 'Tutti i campi obbligatori devono essere compilati';
      return false;
    }

    // Client specific validation
    if (this.activeTab === 'client' && (!this.firstName || !this.lastName)) {
      this.errorMessage = 'Nome e cognome sono obbligatori per i clienti';
      return false;
    }

    // Professional specific validation
    if (this.activeTab === 'professional' && !this.profession) {
      this.errorMessage = 'La professione è obbligatoria per i professionisti';
      return false;
    }

    // Password match validation
    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Le password non corrispondono';
      return false;
    }

    // Email format validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(this.email)) {
      this.errorMessage = 'Formato email non valido';
      return false;
    }

    // Password strength validation
    if (this.password.length < 8) {
      this.errorMessage = 'La password deve contenere almeno 8 caratteri';
      return false;
    }

    return true;
  }

  private clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }
}
