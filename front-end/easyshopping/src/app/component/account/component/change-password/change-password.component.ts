import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  hidePassword: boolean = true;
  hideNewPassword: boolean = true;
  hideConfirmNewPassword: boolean = true;

  changePasswordForm!: FormGroup;
  constructor() { }

  ngOnInit() {
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
