import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class PassswordMatchService {

  constructor() { }


  static passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const newPassword = control.get('newPassword')?.value as string;
    const confirmPassword = control.get('confirmPassword')?.value as string;

    if (newPassword !== confirmPassword) {
      return { 'passwordMismatch': true };
    }

    return null;
  }
}
