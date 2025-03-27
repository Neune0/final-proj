import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfessionalService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAllProfessionals(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/professionals`);
  }

  getProfessionalById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/professionals/${id}`);
  }

  searchProfessionals(criteria: any): Observable<any[]> {
    let params = new HttpParams();
    
    if (criteria.keyword) {
      params = params.set('keyword', criteria.keyword);
    }
    
    if (criteria.category) {
      params = params.set('category', criteria.category);
    }
    
    return this.http.get<any[]>(`${this.apiUrl}/professionals/search`, { params });
  }

  // Questa funzione sarà implementata in un altro componente
  requestConsultation(professionalId: number, requestData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/requests/create`, {
      professionalId,
      ...requestData
    });
  }
}