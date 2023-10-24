import { Component, OnInit } from '@angular/core';
import { AbstractControl, AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth.service';
import { PassswordMatchService } from 'src/app/service/passsword-match.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  hidePassword: boolean = true;
  hideNewPassword: boolean = true;
  hideConfirmNewPassword: boolean = true;

  passwordMismatch: boolean = true;

  changePasswordForm!: FormGroup;
  constructor(private fb: FormBuilder, private authService: AuthService, private toastr: ToastrService, private router: Router) {
    this.changePasswordForm = this.fb.group({
      currentPassword: ['', [
        Validators.required
      ]],
      newPassword: ['', [
        Validators.required
      ]],
      confirmNewPassword: ['', [
        Validators.required
      ]]
    },

    );
  }

  ngOnInit() {

  }


  submitPasswordChange() {

    const newPassword = this.changePasswordForm.get('newPassword')?.value as string;
    const confirmNewPassword = this.changePasswordForm.get('confirmNewPassword')?.value as string;

    if (newPassword === confirmNewPassword) {
      if (this.changePasswordForm.valid) {

        const formData = this.changePasswordForm.value;
        this.authService.updateUserPassword(formData).subscribe({
          next: (response) => {
            // Handle the successful response
            this.toastr.success("Password Updated Successfully.", "SUCCESS!", { timeOut: 2000 });
            this.router.navigate(['account']);
          },
          error: (error) => {
            // Handle API request error
            console.error('Password uppdate failed', error);
            this.toastr.error("Password was unable to be updated", "FAILED!", { timeOut: 2000 });
          }
        });



      } else {
        console.log('Form validation error.')
      }

      this.passwordMismatch = false;
    } else {
      this.passwordMismatch = true;
    }
  }


  togglePasswordVisibility(field: string) {
    switch (field) {
      case 'password':
        this.hidePassword = !this.hidePassword;
        break;
      case 'newPassword':
        this.hideNewPassword = !this.hideNewPassword;
        break;
      case 'confirmNewPassword':
        this.hideConfirmNewPassword = !this.hideConfirmNewPassword;
        break;
    }

  }

}
