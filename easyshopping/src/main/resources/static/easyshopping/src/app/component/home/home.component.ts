import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { routeAnimation } from 'src/route-animations';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [routeAnimation],
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getRouteAnimationState(outlet: RouterOutlet) {
    return outlet.activatedRouteData['animation'] || 'none';
  }

}
