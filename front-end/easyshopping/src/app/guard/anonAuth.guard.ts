import { inject } from '@angular/core'
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ToastrService } from "ngx-toastr";




export const anonAuthGuard = () => {
    const authService = inject(AuthService);
    const route = inject(Router)
    const toastr = inject(ToastrService);

    if (!authService.isAuthenticated()) {
        return true;
    } else {
        toastr.warning("You are already logged in!", "USER LOGGED IN", { timeOut: 3000 });
        route.navigate(['dashboard'])
        return false;
    }
}