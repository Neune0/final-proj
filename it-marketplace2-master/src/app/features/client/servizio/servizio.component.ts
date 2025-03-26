import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  standalone: false,
  selector: 'app-servizio',
  templateUrl: './servizio.component.html',
  styleUrls: ['./servizio.component.css']
})
export class ServizioComponent implements OnInit {
  serviceTitle: string = '';  // Inizializza con un valore vuoto
  serviceDescription: string = '';  // Inizializza con un valore vuoto

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log (this.route)
    this.route.data.subscribe(data => {
      console.log(data) 
      this.serviceTitle = data['title'];
  
      switch (this.serviceTitle) {
        case 'Sviluppo Frontend':
          this.serviceDescription = 'Offriamo soluzioni di sviluppo per applicazioni web moderne utilizzando tecnologie come Angular, React e Vue.';
          break;
        case 'Sviluppo Backend':
          this.serviceDescription = 'Costruiamo sistemi backend robusti con Node.js, Express e soluzioni scalabili per supportare le tue applicazioni.';
          break;
        case 'Gestione Database':
          this.serviceDescription = 'Gestiamo database SQL e NoSQL, garantendo alta disponibilità, sicurezza e ottimizzazione delle query per i tuoi dati.';
          break;
        case 'Marketing':
          this.serviceDescription = 'Aiutiamo a sviluppare strategie di marketing digitale per far crescere il tuo business online, con campagne mirate e analisi dei dati.';
          break;
        default:
          this.serviceDescription = 'Descrizione generica del servizio';
      }
    });
  }
  
}
