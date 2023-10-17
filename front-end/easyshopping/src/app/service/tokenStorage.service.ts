import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  storeToken(tokenValue: string): void {
    localStorage.removeItem('token');
    localStorage.setItem('token', tokenValue);
  }

  storeUsername(username: string): void {
    localStorage.removeItem('username');
    localStorage.setItem('username', username);
  }

  storeRole(role: string): void {
    localStorage.removeItem('role');
    localStorage.setItem('role', role);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  getRole() {
    return localStorage.getItem('role');
  }

  getUsername() {
    return localStorage.getItem('username');
  }

}
