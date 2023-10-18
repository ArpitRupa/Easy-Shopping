import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmUserActionDialogComponent } from '../confirm-user-action-dialog/confirm-user-action-dialog.component';
import { AuthService } from 'src/app/service/auth.service';
import { UserInterface } from 'src/app/interface/user.interface';
import { ToastrService } from "ngx-toastr";


@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.css']
})
export class UserDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public user: UserInterface, public dialog: MatDialog, private authService: AuthService, private toastr: ToastrService) { }

  currentRole = ''

  roles = [{ value: 'USER', viewValue: 'USER' }, { value: 'ADMIN', viewValue: 'ADMIN' }]

  ngOnInit() {
    this.currentRole = this.user.role;
  }

  openConfirmDeleteDialog(user: UserInterface) {
    this.dialog.open(ConfirmUserActionDialogComponent, {
      data: user
    }).afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        // User confirmed with "Yes"
        console.log('User confirmed with "Yes"');
      }
    });
  }

  onRoleSelected(user: UserInterface) {
    console.log(user);
    this.dialog.open(ConfirmUserActionDialogComponent, {
      data: user
    }).afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        let updated_user = user;
        updated_user.role = this.currentRole;
        this.authService.updateUser(updated_user).subscribe({
          next: (response) => {
            // Handle the successful response
            this.toastr.success("Role successfully update.", "SUCCESS!", { timeOut: 3000 });
          },
          error: (error) => {
            // Handle API request error
            console.error('Role Update failed', error);
          }
        });
      } else {
        this.currentRole = this.user.role;
      }
    });
  }
}
