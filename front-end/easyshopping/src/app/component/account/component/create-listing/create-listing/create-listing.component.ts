import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ImageWithPreview } from 'src/app/interface/product/imageWithPreview.interface';
import { ToastrService } from 'ngx-toastr';
import { ProductCreationRequest } from 'src/app/interface/product/product.interface';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-create-listing',
  templateUrl: './create-listing.component.html',
  styleUrls: ['./create-listing.component.css']
})
export class CreateListingComponent implements OnInit {

  selectedFiles: ImageWithPreview[] = [];
  productForm!: FormGroup;


  constructor(private formBuilder: FormBuilder, private toastrService: ToastrService, private productService: ProductService) { }

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

  postListing() {
    if (this.productForm.valid) {
      const listingData: ProductCreationRequest = {

        productRequest: {
          name: this.productForm.value.productName,
          description: this.productForm.value.productDescription,
          price: this.productForm.value.productPrice
        },

        imageRequests: this.selectedFiles.map(image => {
          // convert base64 string to binary data
          const binaryData = atob(image.dataURL.split(',')[1]);
          const imageBinaryData = new Uint8Array(binaryData.length);
          for (let i = 0; i < binaryData.length; i++) {
            imageBinaryData[i] = binaryData.charCodeAt(i);
          }

          return {
            productId: 0,
            imageData: imageBinaryData
          };
        })
      };

      this.productService.createProductListing(listingData).subscribe({
        next: (response) => {
          // Handle the successful response
          const toast = this.toastrService.success("Listing Created Successfully.", "SUCCESS!", { timeOut: 2000 });
          toast.onHidden.subscribe(() =>
            window.location.reload()
          );
        },
        error: (error) => {
          this.toastrService.error("Failed to create listing.", "FAILED!", { timeOut: 2000 });
          // Handle API request error
          console.error('Listing Creation failed', error);
        }
      });
    } else {
      this.toastrService.error("Invalid Information. Please Check fields and try again.", "INVALID FORM", { timeOut: 2000 })
    }
  }


  onFileSelected(event: any) {
    const selectedFiles = event.target.files;
    if (selectedFiles.length > 5) {
      // Enforce the maximum limit of 5 files
      alert('You can only select up to 5 image files.');
      event.target.value = ''; // Clear the file input
      this.selectedFiles = [];
      return;
    }

    this.selectedFiles = [];

    for (let i = 0; i < selectedFiles.length; i++) {
      const file = selectedFiles[i];
      const reader = new FileReader();

      reader.onload = (e) => {
        const dataURL = reader.result as string;
        this.selectedFiles.push({ file, dataURL });
      };

      reader.readAsDataURL(file);
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

