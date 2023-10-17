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

@Injectable()
export class HttpAuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<any>> {
    let requestCopy = request.clone();
    const token = localStorage.getItem('token');

    if (token) {
      requestCopy = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(requestCopy).pipe(
      tap({
        next: (event) => {
          if (event instanceof HttpResponse) {
            if (event.status == 401) {
              this.authService.signOut();
              this.router.navigate(['login']);
              this.toastr.error('You have been logged out for invalidating your JWT; please log back in.',
                'INAUTHENTIC JWT', { timeOut: 4500 });
            }
          }
          return event;
        },
        error: (error) => {
          if (error.status === 401) {
            this.authService.signOut();
            this.router.navigate(['login']);
            this.toastr.error('You token was deemed invalid. Please back in.',
              'Token Error', { timeOut: 4500 });
          }
          else if (error.status === 404) {
            this.router.navigate(['']);
            this.toastr.error('Redirecting to home.',
              '404 Error...', { timeOut: 3000 });
          }
        }
      }));




    // .pipe(
    //   catchError((error: HttpErrorResponse) => {
    //     if (error.status === 401) {
    //       // Token is rejected or expired, log the user out.
    //     }

    //   // return throwError(error);
    //   })
    // )
  }
}
