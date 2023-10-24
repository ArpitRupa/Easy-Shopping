import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from "ngx-toastr"

@Injectable({
  providedIn: 'root'
})
export class HttpAuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = request.clone();
    const token = localStorage.getItem('token');

    if (token) {
      authReq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    } else {
      console.log('NOOOOO')
    }
    return next.handle(authReq).pipe(
      tap({
        next: (event) => {
          if (event instanceof HttpResponse) {
            if (event.status == 401) {
              this.authService.signOut();
              this.router.navigate(['login']);
              this.toastr.error('You have been logged out for invalidating your JWT; please log back in.',
                'INAUTHENTIC JWT', { timeOut: 2000 });
            }
          }
          return event;
        },
        error: (error) => {
          if (error.status === 401) {
            this.authService.signOut();
            this.router.navigate(['login']);
            this.toastr.error('You token was deemed invalid. Please back in.',
              'Token Error', { timeOut: 2000 });
          }
          else if (error.status === 404) {
            this.router.navigate(['']);
            this.toastr.warning('Redirecting to home.',
              '404 Error...', { timeOut: 3000 });
          }
        }
      }));




    // .pipe(
    //   catchError(error => {
    //     if (error.status === 401) {
    //       this.router.navigate(['']);
    //       this.toastr.error('401 from Server; Redirected to home".',
    //         '401', { timeOut: 4500 });
    //     } else if (error.status === 0) {
    //       // Handle CORS error
    //       console.error('CORS error:', error);
    //       //   this.authService.signOut();
    //       //   this.router.navigate(['login']);
    //       //   this.toastr.error('You have been logged out for invalidating your JWT; please log back in.',
    //       //     'INAUTHENTIC JWT', { timeOut: 4500 });
    //     }
    //     return throwError(error);
    //   })
    // );
  }
}
