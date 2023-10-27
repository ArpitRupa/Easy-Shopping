import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmUserActionDialogComponent } from '../../../common/confirm-user-action-dialog/confirm-user-action-dialog.component';
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

  saveInProgress: boolean = false;

  roles = [{ value: 'USER', viewValue: 'USER' }, { value: 'ADMIN', viewValue: 'ADMIN' }]

  ngOnInit() {
    this.saveInProgress = false;
    const overlay = document.getElementById("overlay");
    if (overlay) {
      overlay.style.display = "none";
    }
    this.currentRole = this.user.role;
  }

  openConfirmDeleteDialog(user: UserInterface) {
    this.dialog.open(ConfirmUserActionDialogComponent, {
      data: user
    }).afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        this.authService.deleteUser(user.id).subscribe({
          next: (response) => {
            // Handle the successful response
            const toast = this.toastr.success("User successfully deleted.", "SUCCESS!", { timeOut: 2000, });
            this.saveInProgress = true;
            const overlay = document.getElementById("overlay");
            if (overlay) {
              overlay.style.display = "block";
            }
            toast.onHidden.subscribe(() =>
              window.location.reload()
            );
          },
          error: (error) => {
            // Handle API request error
            console.error('Deletion of User failed', error);
          }
        });

      }
    });
  }

  onRoleSelected(user: UserInterface) {
    console.log(user);
    this.dialog.open(ConfirmUserActionDialogComponent, {
      data: user
    }).afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        this.authService.updateUserRole(user.id, this.currentRole).subscribe({
          next: (response) => {
            const toast = this.toastr.success("Role successfully updated.", "SUCCESS!", { timeOut: 2000 });
            this.saveInProgress = true;
            const overlay = document.getElementById("overlay");
            if (overlay) {
              overlay.style.display = "block";
            }
            toast.onHidden.subscribe(() =>
              window.location.reload()
            );
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
