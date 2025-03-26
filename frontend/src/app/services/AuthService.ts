import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { AuthRequest, AuthResponse, RegisterRequest, User } from '../models/Auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = 'http://localhost:8080/api/auth';
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser$: Observable<User | null>;
  
  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User | null>(this.getUserFromStorage());
    this.currentUser$ = this.currentUserSubject.asObservable();
  }
  
  // Recupera l'utente dal localStorage
  private getUserFromStorage(): User | null {
    const storedUser = localStorage.getItem('currentUser');
    return storedUser ? JSON.parse(storedUser) : null;
  }
  
  // Ottieni il valore corrente dell'utente
  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }
  
  // Esegui il login
  login(username: string, password: string): Observable<User> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, { username, password })
      .pipe(
        map(response => {
          // Trasforma la risposta nel formato User
          console.log(response);
          const user: User = {
            username: response.username,
            role: response.role,
            token: response.accessToken
          };
          
          // Salva nel localStorage e aggiorna il BehaviorSubject
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
          return user;
        }),
        catchError(error => {
          console.error('Login failed:', error);
          return throwError(() => new Error(error.error || 'Login failed'));
        })
      );
  }
  
  // Registra un nuovo client
  registerClient(registerData: RegisterRequest): Observable<string> {
    return this.http.post<string>(`${this.API_URL}/register/client`, registerData)
      .pipe(
        catchError(error => {
          console.error('Client registration failed:', error);
          return throwError(() => new Error(error.error || 'Registration failed'));
        })
      );
  }
  
  // Registra un nuovo professionista
  registerProfessional(registerData: RegisterRequest): Observable<string> {
    return this.http.post<string>(`${this.API_URL}/register/professional`, registerData)
      .pipe(
        catchError(error => {
          console.error('Professional registration failed:', error);
          return throwError(() => new Error(error.error || 'Registration failed'));
        })
      );
  }
  
  // Logout
  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
  
  // Verifica se l'utente è autenticato
  isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }
  
  // Verifica se l'utente ha un ruolo specifico
  hasRole(requiredRole: string | string[]): boolean {
    const user = this.currentUserValue;
    if (!user) return false;
    
    if (Array.isArray(requiredRole)) {
      return requiredRole.some(role => user.role.includes(role));
    }
    
    return user.role.includes(requiredRole);
  }
  
  // Ottieni il token
  getToken(): string | null {
    return this.currentUserValue?.token || null;
  }
}