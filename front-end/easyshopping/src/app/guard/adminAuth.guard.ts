import { inject } from '@angular/core'
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from 'src/app/service/tokenStorage.service';
import { ToastrService } from "ngx-toastr";




export const adminAuthGuard = () => {
    const authService = inject(AuthService);
    const tokenStorageService = inject(TokenStorageService);
    const route = inject(Router)
    const toastr = inject(ToastrService);

    if (tokenStorageService.getRole() == 'ADMIN') {
        return true;
    } else {
        toastr.error("Invalid authorization.", 'Error', { timeOut: 3000 });
        route.navigate(['dashboard'])
        return false;
    }
}