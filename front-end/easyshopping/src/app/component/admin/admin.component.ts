import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { UserDialogComponent } from './component/user-dialog/user-dialog.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  usersArray: any[] = [];

  constructor(private authService: AuthService, public dialog: MatDialog) { }

  ngOnInit() {
    const users = this.authService.getAllUsers().subscribe({
      next: (response) => {
        this.usersArray = Object.values(response);
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      }
    })
  }


  openUserDialog(user: any) {
    this.dialog.open(UserDialogComponent, {
      data: user
    });
  }

}
