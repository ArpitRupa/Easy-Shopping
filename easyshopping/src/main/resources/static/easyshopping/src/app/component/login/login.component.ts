import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { TokenStorageService } from 'src/app/service/tokenStorage.service';
import { routeAnimation } from 'src/route-animations';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { ToastrService } from "ngx-toastr"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [routeAnimation],
})
export class LoginComponent implements OnInit {

  constructor(private builder: FormBuilder, private authService: AuthService, private toastr: ToastrService, private router: Router, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
  }

  loginForm = this.builder.nonNullable.group({
    username: ['', [Validators.pattern('[a-zA-Z][a-zA-Z0-9 ]*'), Validators.required]],
    password: ['', Validators.required],
  });

  public processLogin() {

    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      this.authService.postLogin(formData).subscribe({
        next: (response) => {
          // Handle the successful response
          console.log('Server Response:', response);
          this.tokenStorageService.storeToken(response.jwt);
          this.tokenStorageService.storeUsername(response.username);
          this.tokenStorageService.storeRole(response.role);
          this.loginForm.reset();
          this.toastr.success("Logged in Successfully.", "SUCESS!", { timeOut: 3000 });
          this.router.navigate(['']);
        },
        error: (error) => {
          this.toastr.error('Invalid credentials.');
        }
      });

    } else {
      this.toastr.error('Login failed. Check fields for valid input.');
    }

  }


  public getInLineErrorMessage(controlName: string): string {
    const control = this.loginForm.get(controlName);

    control?.validator?.name

    if (control?.hasError('required')) {
      return 'You must enter a value';
    }

    if (control?.hasError('pattern')) {
      // Extract the pattern from the control's validator configuration
      const pattern = control.errors?.['pattern'].requiredPattern;
      return `Invalid input. Please use the pattern: ${pattern}`;
    }

    if (control?.hasError('email')) {
      return 'Not a valid email';
    }

    return '';
  }

  getRouteAnimationState(outlet: RouterOutlet) {
    return outlet.activatedRouteData['animation'] || 'none';
  }
}
