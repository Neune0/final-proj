import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getClientProfile(): Observable<any> {
    return this.http.get(`${this.apiUrl}/clients/profile`);
  }

  updateClientProfile(profile: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/clients/profile`, profile);
  }

  updateProfileImage(imageBase64: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/clients/profile/image`, imageBase64);
  }

  // Questo endpoint non è direttamente visibile nei controller forniti, ma è probabile che esista
  getPendingRequests(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/clients/requests`);
  }
}