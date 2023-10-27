import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer) {
    this.matIconRegistry.addSvgIcon(
      'linkedin-icon',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/linkedin-50.svg')
    );
    this.matIconRegistry.addSvgIcon(
      'git-icon',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/github-60.svg')
    );
  }

  ngOnInit() {
  }


  openLink(link: string) {
    window.open(link, '_blank');
  }

}
