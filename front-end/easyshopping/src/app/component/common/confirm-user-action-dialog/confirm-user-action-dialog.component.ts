import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { UserInterface } from 'src/app/interface/user.interface';


@Component({
  selector: 'app-confirm-user-action-dialog',
  templateUrl: './confirm-user-action-dialog.component.html',
  styleUrls: ['./confirm-user-action-dialog.component.css']
})
export class ConfirmUserActionDialogComponent implements OnInit {

  userConfirmed: boolean = false;

  constructor(
    public dialogRef: MatDialogRef<ConfirmUserActionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public user: UserInterface) { }

  ngOnInit() {
  }

  closeDialog(confirmed: boolean) {
    this.userConfirmed = confirmed;
    this.dialogRef.close(this.userConfirmed);
  }

}
