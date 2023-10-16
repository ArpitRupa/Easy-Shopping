import { inject } from '@angular/core'
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ToastrService } from "ngx-toastr";




export const authGuard = () => {
  const authService = inject(AuthService);
  const route = inject(Router)
  const toastr = inject(ToastrService);

  if (authService.isAuthenticated()) {
    return true;
  } else {
    toastr.error("Invalid authorization.");
    route.navigate(['login'])
    return false;
  }
}