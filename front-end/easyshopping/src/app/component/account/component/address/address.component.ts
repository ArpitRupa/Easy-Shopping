import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserInterface } from 'src/app/interface/user.interface';
import { AuthService } from 'src/app/service/auth.service';
import { AddressFormComponent } from './component/address-form/address-form.component';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  constructor(private authService: AuthService, public dialog: MatDialog) { }

  ngOnInit() {
  }



  openAddressInputDialog() {
    this.dialog.open(AddressFormComponent);
  }

}
