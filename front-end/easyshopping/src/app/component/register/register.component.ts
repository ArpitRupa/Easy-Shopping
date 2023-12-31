import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from "ngx-toastr"
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { routeAnimation } from 'src/route-animations';
import { RouterOutlet } from '@angular/router';
import { passwordMatchValidator } from 'src/app/service/passsword-match.service';
import { RegistrationRequestInterface } from 'src/app/interface/registrationRequest.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  animations: [routeAnimation],
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  passwordMismatch: boolean = true;

  constructor(private builder: FormBuilder, private toastr: ToastrService, private router: Router, private authService: AuthService) {

    this.registerForm = this.builder.group({
      firstName: ['', [Validators.pattern('[a-zA-Z ]*'), Validators.required]],
      lastName: ['', [Validators.pattern('[a-zA-Z ]*'), Validators.required]],
      username: ['', [Validators.pattern('[a-zA-Z][a-zA-Z0-9 ]*'), Validators.required]],
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    },
      { validator: passwordMatchValidator('password', 'confirmPassword') }
    );
  }

  ngOnInit() {
  }



  public processRegistration() {
    const password = this.registerForm.get('password')?.value as string;
    const confirmPassword = this.registerForm.get('confirmPassword')?.value as string;

    if (password === confirmPassword) {

      this.passwordMismatch = false;

      if (this.registerForm.valid) {
        const formData = this.registerForm.value;

        const registration: RegistrationRequestInterface = {

          firstName: formData.firstName,
          lastName: formData.lastName,
          username: formData.username,
          email: formData.email,
          password: formData.password,
        };

        this.authService.postRegistration(registration).subscribe({
          next: (response) => {
            // Handle the successful response
            this.toastr.success("Registered Successfully.");
            this.router.navigate(['login']);
          },
          error: (error) => {
            // Handle API request error
            console.error('Registration failed', error);
          }
        });


      }
      else {
        for (const field in this.registerForm.controls) {
          const formControl = this.registerForm.get(field)!;
          if (formControl.errors) {
            this.toastr.warning(this.getErrorMessage(formControl, field));
          }
        }
      }
    } else {
      this.passwordMismatch = true;
    }
  }


  private getErrorMessage(control: AbstractControl, field: string): string {

    if (control.hasError('required')) {
      return `The field ${field} is required.`;
    } else if (control.hasError('pattern')) {
      return `Invalid input at ${field}. Please check and try again.`;
    } else if (control.hasError('email')) {
      return 'Invalid email format. Please enter a valid email address.';
    }

    return 'Invalid input.';
  }


  public getInLineErrorMessage(controlName: string): string {
    const control = this.registerForm.get(controlName);

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
