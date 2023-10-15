import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationData } from '../interface/registrationData';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  private apiUrl = "http://localhost:8080/api"

  getAll() {
    return this.http.get(this.apiUrl);
  }


  postRegistration(inputData: Partial<RegistrationData>) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    return this.http.post(this.apiUrl + '/users/register', inputData, httpOptions).pipe(
      catchError((error) => {
        console.error('Registration failed', error);
        // Return an observable with an error or some fallback value if needed
        return throwError(() => new error('Registration failed'));
      })
    );
  }

}
