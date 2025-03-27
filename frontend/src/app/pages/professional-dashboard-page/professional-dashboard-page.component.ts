import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-professional-dashboard-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './professional-dashboard-page.component.html',
  styleUrls: ['./professional-dashboard-page.component.css']
})
export class ProfessionalDashboardPageComponent {
  professionalProfile = {
    firstName: 'Luca',
    lastName: 'Verdi',
    email: 'luca.verdi@example.com',
    profileImage: ''
  };

  activeProjects: number = 3;
  hoursThisMonth: number = 42;
  averageRating: number = 4.7;

  pendingRequests = [
    { id: 1 },
    { id: 2 },
    { id: 3 }
  ];

  logout() {
    console.log('Logout eseguito');
    // Qui collegherai il backend in futuro
  }
}
