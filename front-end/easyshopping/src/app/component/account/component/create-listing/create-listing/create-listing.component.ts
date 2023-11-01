import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-listing',
  templateUrl: './create-listing.component.html',
  styleUrls: ['./create-listing.component.css']
})
export class CreateListingComponent implements OnInit {

  selectedFiles: File[] = [];
  productForm!: FormGroup;


  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.productForm = this.formBuilder.group({
      productName: ['', [
        Validators.pattern('[a-zA-Z0-9 ]*'),
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(50)
      ]],
      productDescription: ['', [
        Validators.pattern('[a-zA-Z0-9,.!?\'" ]+'),
        Validators.minLength(5),
        Validators.maxLength(5000)
      ]],
      productPrice: ['', [
        Validators.pattern('[0-9]*'),
        Validators.required,
      ]],
      productImages: ['', [
        Validators.required
      ]]
    });
  }


  onFileSelected(event: any) {
    const selectedFiles = event.target.files;
    if (selectedFiles.length > 5) {
      // Enforce the maximum limit of 5 files
      alert('You can only select up to 5 image files.');
      event.target.value = ''; // Clear the file input
      this.selectedFiles = [];
      return;
    } else {
      this.selectedFiles = selectedFiles;
    }
  }


  validateInput(fieldName: string) {
    const control = this.productForm.get(fieldName);

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
