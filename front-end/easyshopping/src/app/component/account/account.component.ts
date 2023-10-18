import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  userObject = {
    id: '',
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    role: ''
  };

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.getUser().subscribe({
      next: (response) => {
        // Handle the successful response
        this.userObject.id = response.id;
        this.userObject.username = response.username;
        this.userObject.firstName = response.firstName;
        this.userObject.lastName = response.lastName;
        this.userObject.email = response.email;
        this.userObject.role = response.role;
      },
      error: (error) => {
        // Handle API request error
        console.error('Loading user data failed', error);
      }
    });
  }

}
