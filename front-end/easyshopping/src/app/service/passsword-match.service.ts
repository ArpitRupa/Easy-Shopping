import { Injectable } from '@angular/core';
import { AbstractControl, ValidatorFn } from '@angular/forms';


export function passwordMatchValidator(field1: string, field2: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const password = control.get(field1);
    const confirmPassword = control.get(field2);

    if (password && confirmPassword && password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ 'passwordMismatch': true });
      return { 'passwordMismatch': true };
    } else {
      confirmPassword!.setErrors(null);
      return null;
    }
  };
}
