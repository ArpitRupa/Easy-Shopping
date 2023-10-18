import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationRequestInterface } from '../interface/registrationRequest.interface';
import { LoginRequestInterface } from '../interface/loginRequest.interface';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { UserInterface } from '../interface/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,) { }

  private apiUrl = "http://localhost:8080"

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  getUser() {
    return this.http.get<any>(this.apiUrl + "/api/users/username/" + localStorage.getItem('username'))
      .pipe(
        catchError((error) => {
          console.error('Get for username failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Get for username failed'));
        })
      );
  }

  getAllUsers() {
    return this.http.get<any>(this.apiUrl + "/api/users")
      .pipe(
        catchError((error) => {
          console.error('Error getting all users.', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Error getting all users'));
        })
      );
  }


  postRegistration(inputData: Partial<RegistrationRequestInterface>) {
    return this.http.post<any>(this.apiUrl + '/api/users/register', inputData, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.error('Registration failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Registration failed'));
        })
      );
  }


  postLogin(inputData: Partial<LoginRequestInterface>) {

    return this.http.post<any>(this.apiUrl + '/auth/login', inputData, this.httpOptions)
      .pipe(
        catchError((error) => {
          // Return an observable with an error or some fallback value if needed
          return error;
        })
      );
  }

  updateUser(inputData: UserInterface) {

    return this.http.put<any>(this.apiUrl + '/api/users/' + inputData.id, inputData, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.log(error);
          return error;
        })
      );
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return token !== null && token !== '';
  }

  signOut(): void {
    localStorage.clear();
  }
}
