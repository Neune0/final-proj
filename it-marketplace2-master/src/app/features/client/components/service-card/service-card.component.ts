import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-service-card',
  standalone: false, // <--- cambia qui
  templateUrl: './service-card.component.html',
  styleUrls: ['./service-card.component.css'] // <- piccolo errore: è `styleUrls`, non `styleUrl`
})
export class ServiceCardComponent {
  @Input() service: any; // assicurati di avere questo se passi [service]="s"
}
