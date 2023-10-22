import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/service/auth.service';
import { UserInterface } from 'src/app/interface/user.interface';
import { catchError, of, switchMap, throwError } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-info-form',
  templateUrl: './update-info-form.component.html',
  styleUrls: ['./update-info-form.component.css']
})
export class UpdateInfoFormComponent implements OnInit {

  infoForm!: FormGroup;
  user!: UserInterface;

  constructor(private location: Location, private route: ActivatedRoute, private formBuilder: FormBuilder, private authService: AuthService, private toastr: ToastrService) { }

  ngOnInit() {

    const state = this.location.getState() as any;
    this.user = state.userObject;

    this.infoForm = this.formBuilder.group({
      firstName: [this.user.firstName ? this.user.firstName : '', [
        Validators.pattern('[a-zA-Z ]*'), Validators.required
      ]],
      lastName: [this.user.lastName ? this.user.lastName : '', [
        Validators.pattern('[a-zA-Z ]*'), Validators.required
      ]],
      email: [this.user.email ? this.user.email : '', [
        Validators.email, Validators.required
      ]],
    })

  }



  processInfoUpdate() {

    if (this.infoForm.valid) {
      const formData = this.infoForm.value;

      this.authService.updateUserInfo(formData, this.user.id).subscribe({
        next: (response) => {
          // Handle the successful response
          const toast = this.toastr.success("Info Updated Successfully.", "SUCCESS!", { timeOut: 2000 });
          toast.onHidden.subscribe(() =>
            window.location.reload()
          );
        },
        error: (error) => {
          // Handle API request error
          console.error('Update failed.', error);
          this.toastr.error("Info could not be updated.", "FAILED!", { timeOut: 2000 });
        }
      });

    }

  }

}
