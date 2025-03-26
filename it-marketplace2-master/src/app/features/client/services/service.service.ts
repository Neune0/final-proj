import { Injectable } from '@angular/core';
import { Service } from '../models/service.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  private services: Service[] = [
    {
      id: 1,
      title: 'Sviluppo Frontend Angular',
      description: 'Crea SPA moderne con Angular 17.',
      image: 'https://via.placeholder.com/300x200',
      price: 40
    },
    {
      id: 2,
      title: 'API REST con Node.js',
      description: 'Costruisci backend scalabili con Express.',
      image: 'https://via.placeholder.com/300x200',
      price: 50
    },
    {
      id: 3,
      title: 'Consulenza DevOps',
      description: 'Ottimizza il tuo flusso CI/CD.',
      image: 'https://via.placeholder.com/300x200',
      price: 60
    }
  ];

  getServices(): Service[] {
    return this.services;
  }
}
