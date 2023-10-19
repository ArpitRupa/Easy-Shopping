import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { StateService } from '../../../Service/state.service';

@Component({
  selector: 'app-address-form',
  templateUrl: './address-form.component.html',
  styleUrls: ['./address-form.component.css']
})
export class AddressFormComponent implements OnInit {

  addressForm!: FormGroup
  states!: { code: string, name: string }[]

  constructor(private formBuilder: FormBuilder, private stateService: StateService, public dialogRef: MatDialogRef<AddressFormComponent>) { }

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

  }

  processClose() {
    this.dialogRef.close();
  }

}
