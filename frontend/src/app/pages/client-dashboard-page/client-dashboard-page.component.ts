import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClientService } from '../../services/ClientService';
import { ProfessionalService } from '../../services/ProfessionalService';
import { AuthService } from '../../services/AuthService';
@Component({
  selector: 'app-client-dashboard-page',
  imports: [CommonModule, RouterLink, RouterOutlet, FormsModule, HttpClientModule],
  templateUrl: './client-dashboard-page.component.html',
  styleUrl: './client-dashboard-page.component.css'
})
export class ClientDashboardPageComponent implements OnInit {
  clientProfile: any = null;
  loading = true;
  errorMessage = '';
  
  // Professionisti in evidenza
  featuredProfessionals: any[] = [];
  
  // Richieste di appuntamento
  pendingRequests: any[] = [];
  
  // Criteri di ricerca
  searchTerm = '';
  selectedCategory = '';
  
  // Categorie di servizi IT
  categories = [
    'Sviluppo Web',
    'Sviluppo Mobile',
    'Cybersecurity',
    'Cloud Services',
    'Data Science',
    'DevOps',
    'UI/UX Design',
    'Reti e Infrastrutture',
    'Consulenza IT'
  ];
  
  constructor(
    private clientService: ClientService,
    private professionalService: ProfessionalService,
    private authService: AuthService
  ) {}
  
  ngOnInit(): void {
    this.loadClientProfile();
    this.loadFeaturedProfessionals();
    this.loadPendingRequests();
  }
  
  loadClientProfile(): void {
    this.clientService.getClientProfile().subscribe({
      next: (profile) => {
        this.clientProfile = profile;
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = 'Errore nel caricamento del profilo: ' + error.message;
        this.loading = false;
      }
    });
  }
  
  loadFeaturedProfessionals(): void {
    this.professionalService.getAllProfessionals().subscribe({
      next: (professionals) => {
        // Mostra solo i primi 4 professionisti come "in evidenza"
        this.featuredProfessionals = professionals.slice(0, 4);
      },
      error: (error) => {
        console.error('Errore nel caricamento dei professionisti:', error);
      }
    });
  }
  
  loadPendingRequests(): void {
    this.clientService.getPendingRequests().subscribe({
      next: (requests) => {
        this.pendingRequests = requests;
      },
      error: (error) => {
        console.error('Errore nel caricamento delle richieste:', error);
      }
    });
  }
  
  searchProfessionals(): void {
    const criteria = {
      keyword: this.searchTerm,
      category: this.selectedCategory || undefined
    };
    
    this.professionalService.searchProfessionals(criteria).subscribe({
      next: (results) => {
        // Navigare a una pagina di risultati o aggiornare una sezione della dashboard
        console.log('Risultati ricerca:', results);
      },
      error: (error) => {
        console.error('Errore nella ricerca:', error);
      }
    });
  }
  
  logout(): void {
    this.authService.logout();
    // Il router gestito dall'authService redirect a login
  }
}
