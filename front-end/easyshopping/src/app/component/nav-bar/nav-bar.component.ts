import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { NavigationEnd, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/service/tokenStorage.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isLoggedIn: boolean = false;
  role = "";
  constructor(private authService: AuthService, private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isLoggedIn = this.authService.isAuthenticated();
        this.role = this.token.getRole() || "none";
      }

    });
  }

  handleLogout() {
    this.authService.signOut();
    this.router.navigate(['login'])
  }
}