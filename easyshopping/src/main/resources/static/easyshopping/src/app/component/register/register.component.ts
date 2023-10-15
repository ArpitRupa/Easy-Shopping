import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from "ngx-toastr"
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private builder: FormBuilder, private toastr: ToastrService, private router: Router, private authService: AuthService) { }

  ngOnInit() {
  }

  registerForm = this.builder.nonNullable.group({
    firstName: ['', [Validators.pattern('[a-zA-Z ]*'), Validators.required]],
    lastName: ['', [Validators.pattern('[a-zA-Z ]*'), Validators.required]],
    username: ['', [Validators.pattern('[a-zA-Z][a-zA-Z0-9 ]*'), Validators.required]],
    email: ['', [Validators.email, Validators.required]],
    password: ['', Validators.required],
    role: 'USER'
  });

  processRegistration() {
    if (this.registerForm.valid) {
      const formData = this.registerForm.value;

      console.log(this.authService.postRegistration(formData));

      this.toastr.success("Registered Successfully.");
      this.router.navigate(['login']);
    }
    else {
      for (const field in this.registerForm.controls) {
        const formControl = this.registerForm.get(field)!;
        if (formControl.errors) {
          this.toastr.warning(this.getErrorMessage(formControl, field));
        }
      }
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
}
