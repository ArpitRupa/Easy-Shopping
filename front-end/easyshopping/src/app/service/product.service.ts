import { Injectable } from '@angular/core';
import { ProductCreationRequest } from '../interface/product/product.interface';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient,) { }

  private apiUrl = "http://localhost:8080"

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=UTF-8',
    })
  };


  createProductListing(productCreation: ProductCreationRequest) {
    return this.http.post<any>(this.apiUrl + '/api/products/create', productCreation, this.httpOptions)
      .pipe(
        catchError((error) => {
          console.error('Creation of Product Listing failed', error);
          // Return an observable with an error or some fallback value if needed
          return throwError(() => new error('Creation of Product Listing failed'));
        })
      );
  }

}


