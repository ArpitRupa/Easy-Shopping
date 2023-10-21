import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AddressInterface } from '../interface/address.interface';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient,) { }

  private apiUrl = "http://localhost:8080"

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };


  createAddress(inputData: AddressInterface) {

    return this.http.post<any>(this.apiUrl + '/api/addresses/create', inputData, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.error('Creation of address failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Creation of address failed'));
        })
      );
  }

  getUserAddresses() {
    return this.http.get<any>(this.apiUrl + '/api/addresses/user', this.httpOptions)
      .pipe(
        catchError((error) => {
          console.error('Get for user addresses failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Get for user addresses failed'));
        })
      );
  }

  deleteAddress(id: number) {
    return this.http.delete<any>(this.apiUrl + '/api/addresses/' + id, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.log(error);
          return error;
        })
      );
  }

  updateAddress(address: AddressInterface) {
    return this.http.put<any>(this.apiUrl + '/api/addresses/' + address.id, address, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.log(error);
          return error;
        })
      );
  }

}
