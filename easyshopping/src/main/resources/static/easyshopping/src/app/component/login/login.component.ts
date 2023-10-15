import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
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

  constructor(private builder: FormBuilder, private authService: AuthService, private toastr: ToastrService, private router: Router,) { }

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
          this.authService.storeToken(response.jwt)
          this.loginForm.reset();
          this.toastr.success("Logged in Successfully.");
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
