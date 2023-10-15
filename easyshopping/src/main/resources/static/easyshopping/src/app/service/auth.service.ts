import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationData } from '../interface/registrationData';
import { LoginData } from '../interface/LoginData';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private apiUrl = "http://localhost:8080"

  getAll() {
    return this.http.get(this.apiUrl);
  }


  postRegistration(inputData: Partial<RegistrationData>) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    return this.http.post<any>(this.apiUrl + '/api/users/register', inputData, httpOptions)
      .pipe(
        catchError((error) => {
          console.error('Registration failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Registration failed'));
        })
      );
  }


  postLogin(inputData: Partial<LoginData>) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };

    return this.http.post<any>(this.apiUrl + '/auth/login', inputData, httpOptions)
      .pipe(
        catchError((error) => {
          // Return an observable with an error or some fallback value if needed
          return error;
        })
      );
  }


  storeToken(tokenValue: string) {
    localStorage.setItem('token', tokenValue);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedin(): boolean {
    const token = localStorage.getItem('token');
    return token !== null && token !== '';
  }
}
