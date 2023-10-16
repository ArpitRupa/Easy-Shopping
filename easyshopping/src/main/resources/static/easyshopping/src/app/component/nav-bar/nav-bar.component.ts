import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isLoggedIn: boolean = false;
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.isLoggedIn = this.authService.isAuthenticated();
  }

}
