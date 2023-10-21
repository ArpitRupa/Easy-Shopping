import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { StateService } from '../../../Service/state.service';
import { ToastrService } from 'ngx-toastr';
import { AddressInterface } from 'src/app/interface/address.interface';
import { AddressService } from 'src/app/service/address.service';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent implements OnInit {


  @Input() initialAddress: AddressInterface | undefined;

  addressForm!: FormGroup
  states!: { code: string, name: string }[]

  constructor(
    private addressService: AddressService,
    private formBuilder: FormBuilder,
    private stateService: StateService,
    public dialogRef: MatDialogRef<AddressFormComponent>,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.addressForm = this.formBuilder.group({
      street1: [this.initialAddress ? this.initialAddress.shippingAddressLine1 : '', [
        Validators.pattern('[a-zA-Z0-9 ]*'),
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      street2: [this.initialAddress ? this.initialAddress.shippingAddressLine2 : '', [
        Validators.pattern('[a-zA-Z0-9 ]*'),
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      city: [this.initialAddress ? this.initialAddress.city : '', [
        Validators.pattern('[a-zA-Z]*'),
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
      state: [this.initialAddress ? this.initialAddress.stateName : '', [
        Validators.pattern('[a-zA-Z]*'),
        Validators.required
      ]],
      zipCode: [this.initialAddress ? this.initialAddress.postalCode : '', [
        Validators.pattern('[0-9]*'),
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(5)
      ]],
    })

    this.states = this.stateService.getStates()
  }


  processSubmit() {
    if (this.addressForm.valid) {

      let address: AddressInterface = {
        id: 0,
        shippingAddressLine1: this.addressForm.get('street1')?.value as string,
        shippingAddressLine2: this.addressForm.get('street2')?.value as string,
        city: this.addressForm.get('city')?.value as string,
        stateName: this.addressForm.get('state')?.value as string,
        postalCode: this.addressForm.get('zipCode')?.value as string,
      };

      if (this.initialAddress) {
        address.id = this.initialAddress.id;
        this.addressService.updateAddress(address).subscribe({
          next: (response) => {
            // Handle the successful response
            const toast = this.toastr.success("Address Updated Successfully.", "SUCCESS!", { timeOut: 2000 });
            toast.onHidden.subscribe(() =>
              window.location.reload()
            );
          },
          error: (error) => {
            this.toastr.error("Failed to Update address.", "FAILED!", { timeOut: 2000 });
            // Handle API request error
            console.error('Address Update failed', error);
          }
        });
      } else {
        this.addressService.createAddress(address).subscribe({
          next: (response) => {
            // Handle the successful response
            const toast = this.toastr.success("Address created Successfully.", "SUCCESS!", { timeOut: 2000 });
            toast.onHidden.subscribe(() =>
              window.location.reload()
            );
          },
          error: (error) => {
            this.toastr.error("Failed to create address.", "FAILED!", { timeOut: 2000 });
            // Handle API request error
            console.error('Address Creation failed', error);
          }
        });
      }




    } else {

      this.toastr.error("Invalid Information. Please Check fields and try again.", "INVALID FORM", { timeOut: 2000 })
    }
  }

  processClose() {
    this.dialogRef.close();
  }


  validateInput(fieldName: string) {
    const control = this.addressForm.get(fieldName);

    if (control) {

      if (control.hasError('pattern')) {
        const inputValue = control.value as string;
        if (inputValue.length > 0) {
          control.reset();
          // Remove the last character
          control.setValue(inputValue.slice(0, -1));
        }
      }

      if (control.hasError('maxlength')) {
        const inputValue = control.value as string;
        control.reset();
        control.setValue(inputValue.slice(0, -1));
      }

    }

  }
}
