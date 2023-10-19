import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { StateService } from '../../../Service/state.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent implements OnInit {

  addressForm!: FormGroup
  states!: { code: string, name: string }[]

  constructor(private formBuilder: FormBuilder, private stateService: StateService, public dialogRef: MatDialogRef<AddressFormComponent>, private toastr: ToastrService) { }

  ngOnInit() {
    this.addressForm = this.formBuilder.group({
      street1: ['', [
        Validators.pattern('[a-zA-Z0-9 ]*'),
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      street2: ['', [
        Validators.pattern('[a-zA-Z0-9 ]*'),
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      city: ['', [
        Validators.pattern('[a-zA-Z]*'),
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(50)
      ]],
      state: ['', [Validators.pattern('[a-zA-Z]*'), Validators.required]],
      zipCode: ['', [
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

      const address = this.addressForm.value;
      console.log(address);

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
