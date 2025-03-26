import { Component, OnInit } from '@angular/core';
import { Service } from '../../models/service.model';
import { ServiceService } from '../../services/service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: false,
})
export class HomeComponent implements OnInit {
  services: Service[] = [];

  constructor(private serviceService: ServiceService) {}

  ngOnInit(): void {
    console.log ("test")  
     this.services = this.serviceService.getServices();
    console.log(this.services);  // 👈 Questo è il log che ci aiuta a verificare i dati
  }
}
