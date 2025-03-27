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
    console.log("auth service constructor called");
    this.currentUserSubject = new BehaviorSubject<User | null>(this.getUserFromStorage());
    this.currentUser$ = this.currentUserSubject.asObservable();
  }
  
  // Recupera l'utente dal localStorage
  private getUserFromStorage(): User | null {
    console.log("getUserFromStorage method in auth service");
    const storedUser = localStorage.getItem('currentUser');
    return storedUser ? JSON.parse(storedUser) : null;
  }
  
  // Ottieni il valore corrente dell'utente
  public get currentUserValue(): User | null {
    console.log("getcurrentUserValue method in auth service");
    return this.currentUserSubject.value;
  }
  
  // Esegui il login
  login(username: string, password: string): Observable<User> {
    console.log("entered method login in authservice")
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
    console.log("entered register method in authservice");
    return this.http.post(`${this.API_URL}/register/client`, registerData, { responseType: 'text'})
      .pipe(
        tap(response => console.log("response in registrazione client: ",response)),
        catchError(error => {
          console.error('Client registration failed:', error);
          return throwError(() => new Error(error.error || 'Registration failed'));
        })
      );
  }
  
  // Registra un nuovo professionista
  registerProfessional(registerData: RegisterRequest): Observable<string> {
    console.log("entered register professional method in authservice");
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
    console.log("call logout in authservice")
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
  
  // Verifica se l'utente è autenticato
  isAuthenticated(): boolean {
    console.log("call isauthenticated in authservice");
    return !!this.currentUserValue;
  }
  
  // Verifica se l'utente ha un ruolo specifico
  hasRole(requiredRole: string | string[]): boolean {
    console.log("call has role in authservice");
    const user = this.currentUserValue;
    if (!user) return false;
    
    if (Array.isArray(requiredRole)) {
      return requiredRole.some(role => user.role.includes(role));
    }
    
    return user.role.includes(requiredRole);
  }
  
  // Ottieni il token
  getToken(): string | null {
    console.log("call gettoken in authservice");
    return this.currentUserValue?.token || null;
  }
}