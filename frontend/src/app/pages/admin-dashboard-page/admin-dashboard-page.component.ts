import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dashboard-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard-page.component.html',
  styleUrls: ['./admin-dashboard-page.component.css']
})
export class AdminDashboardPageComponent implements OnInit {
  adminProfile = {
    firstName: 'Stefano',
    lastName: 'Admin',
    email: 'admin@dashboard.it',
    profileImage: 'assets/img/admin-avatar.png'
  };

  usersCount = 150;
  professionalsCount = 42;
  totalRequests = 312;

  pendingRequests = [
    { status: 'PENDING' },
    { status: 'ACCEPTED' }
  ];

  recentRequests = [
    {
      clientName: 'Mario Bianchi',
      professionalName: 'Giulia Dev',
      service: 'Sviluppo Web',
      date: new Date('2025-03-25T10:30:00'),
      status: 'PENDING'
    },
    {
      clientName: 'Luisa Verdi',
      professionalName: 'Marco UI',
      service: 'UX Design',
      date: new Date('2025-03-24T16:00:00'),
      status: 'ACCEPTED'
    },
    {
      clientName: 'Alberto Neri',
      professionalName: 'Francesca Cloud',
      service: 'Cloud Services',
      date: new Date('2025-03-22T09:15:00'),
      status: 'REJECTED'
    }
  ];

  constructor() {}

  ngOnInit(): void {}

  logout(): void {
    console.log('Logout eseguito');
  }
}
