import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs';
import { ConfirmUserActionDialogComponent } from 'src/app/component/common/confirm-user-action-dialog/confirm-user-action-dialog.component';
import { AddressInterface } from 'src/app/interface/address.interface';
import { AddressService } from 'src/app/service/address.service';
import { AddressFormComponent } from './component/address-form/address-form.component';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  addressArray: AddressInterface[] = [];
  saveInProgress: boolean = false;

  constructor(private addressService: AddressService, public dialog: MatDialog, private toastr: ToastrService) { }

  ngOnInit() {
    this.addressService.getUserAddresses().pipe(
      map((addresses: any[]) => addresses.map(this.mapToAddressInterface))
    ).subscribe({
      next: (addresses: AddressInterface[]) => {
        this.addressArray = addresses; // Set the addressArray to the mapped data
      },
      error: error => {
        console.error('Error fetching addresses:', error);
      }
    });
  }



  openAddressInputDialog(address?: AddressInterface) {
    console.log(address);
    let dialogRef = this.dialog.open(AddressFormComponent);
    dialogRef.componentInstance.initialAddress = address;
    // https://stackoverflow.com/questions/54939352/passing-parameter-to-mat-dialog-open-method
  }


  private mapToAddressInterface(data: any): AddressInterface {
    return {
      id: data.addressId,
      shippingAddressLine1: data.shippingAddressLine1,
      shippingAddressLine2: data.shippingAddressLine2,
      city: data.city,
      stateName: data.stateName,
      postalCode: data.postalCode,
    };
  }


  openConfirmDeleteDialog(address: AddressInterface) {
    this.dialog.open(ConfirmUserActionDialogComponent, {
      data: address
    }).afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        this.addressService.deleteAddress(address.id).subscribe({
          next: (response) => {
            // Handle the successful response
            const toast = this.toastr.success("Address successfully deleted.", "SUCCESS!", { timeOut: 2000, });
            this.saveInProgress = true;
            const overlay = document.getElementById("overlay");
            if (overlay) {
              overlay.style.display = "block";
            }
            toast.onHidden.subscribe(() =>
              window.location.reload()
            );
          },
          error: (error) => {
            // Handle API request error
            console.error('Deletion of Address failed', error);
          }
        });

      }
    });
  }

}
