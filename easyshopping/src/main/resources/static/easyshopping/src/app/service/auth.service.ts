import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationData } from '../interface/registrationData';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  apiUrl = 'http://localhost:8080/api/users/register/'

  getAll() {
    return this.http.get(this.apiUrl);
  }


  postRegistration(inputData: Partial<RegistrationData>) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
    return this.http.post(this.apiUrl, inputData, httpOptions).subscribe(
      (response) => {
        // Handle the response
        console.log('Server Response:', response);
      },
      (error) => {
        // Handle API request error
        console.error('Registration failed', error);
      }
    );
    //   (response) => {
    //     // Handle a successful response from the API
    //     console.log('Registration successful', response);
    //   },
    //   (error) => {
    //     // Handle API request error
    //     console.error('Registration failed', error);
    //   }
    // );
  }

}
