import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddressInterface } from 'src/app/interface/address.interface';
import { AddressService } from 'src/app/service/address.service';
import { AddressFormComponent } from './component/address-form/address-form.component';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  address!: []

  constructor(private addressService: AddressService, public dialog: MatDialog) { }

  ngOnInit() {
  }



  openAddressInputDialog() {
    this.dialog.open(AddressFormComponent);
  }

}
