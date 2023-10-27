import { Component, OnInit, HostBinding, HostListener } from '@angular/core';
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
  isDesktop: boolean = false;

  @HostBinding('style.width.px') width = 100;
  @HostBinding('style.height.px') height = 300;

  constructor(private authService: AuthService, private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isLoggedIn = this.authService.isAuthenticated();
        this.role = this.token.getRole() || "none";
      }

    });

    this.isDesktop = window.innerWidth >= 800;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event) {
    this.width = (event.target as Window).innerWidth;
    this.height = (event.target as Window).innerHeight;

    this.isDesktop = this.width >= 800;
  }

  handleLogout() {
    this.authService.signOut();
    this.router.navigate(['login'])
  }
}