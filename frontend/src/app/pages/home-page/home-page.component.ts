import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NavBarComponent } from '../../layout/nav-bar/nav-bar.component';
import {FooterComponent} from '../../layout/footer/footer.component';


@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, RouterLink, NavBarComponent, FooterComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  featuredServices = [
    {
      title: 'Sviluppo Web',
      description: 'Siti web responsivi, e-commerce e applicazioni web personalizzate',
      icon: 'bi-globe'
    },
    {
      title: 'Sviluppo App',
      description: 'Applicazioni mobile native e cross-platform per iOS e Android',
      icon: 'bi-phone'
    },
    {
      title: 'Cybersecurity',
      description: 'Protezione dei dati, valutazione della vulnerabilità e consulenza sulla sicurezza',
      icon: 'bi-shield-lock'
    },
    {
      title: 'Cloud Computing',
      description: 'Migrazione al cloud, ottimizzazione e gestione dell\'infrastruttura',
      icon: 'bi-cloud'
    }
  ];

  // Array di testimonianze
  testimonials = [
    {
      quote: 'Ho trovato un esperto in cybersecurity in pochissimo tempo. Professionale e competente!',
      author: 'Marco R.',
      role: 'CEO, TechSolutions',
      avatar: 'assets/img/testimonial-1.jpg'
    },
    {
      quote: 'Come sviluppatore freelance, questa piattaforma mi ha aiutato a trovare nuovi clienti e opportunità.',
      author: 'Laura B.',
      role: 'Full-Stack Developer',
      avatar: 'assets/img/testimonial-2.jpg'
    },
    {
      quote: 'Interfaccia intuitiva e contatti di qualità. Ottimo servizio!',
      author: 'Antonio G.',
      role: 'CTO, StartupInnovativa',
      avatar: 'assets/img/testimonial-3.jpg'
    }
  ];

  // Array di FAQ
  faqs = [
    {
      question: 'Come funziona la piattaforma?',
      answer: 'La nostra piattaforma mette in contatto professionisti IT con aziende e privati che necessitano di consulenza. I clienti possono pubblicare i loro progetti o cercare direttamente tra i profili dei professionisti. I professionisti possono creare un profilo dettagliato e candidarsi ai progetti disponibili.',
      isOpen: false
    },
    {
      question: 'Quali tipi di servizi IT posso trovare?',
      answer: 'La nostra piattaforma copre una vasta gamma di servizi IT: sviluppo web e app, cybersecurity, cloud computing, data science, intelligenza artificiale, consulenza IT, supporto tecnico e molto altro.',
      isOpen: false
    },
    {
      question: 'Come vengono selezionati i professionisti?',
      answer: 'Tutti i professionisti che si registrano sulla nostra piattaforma passano attraverso un processo di verifica che include la validazione delle competenze, delle esperienze professionali e delle recensioni precedenti.',
      isOpen: false
    },
    {
      question: 'Quanto costa utilizzare la piattaforma?',
      answer: 'La registrazione e la ricerca sono gratuite. Applichiamo una piccola commissione solo quando un progetto viene completato con successo attraverso la nostra piattaforma.',
      isOpen: false
    }
  ];

  toggleFaq(faq: any): void {
    faq.isOpen = !faq.isOpen;
  }
}



