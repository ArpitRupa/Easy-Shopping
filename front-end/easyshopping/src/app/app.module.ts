import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { ReactiveFormsModule } from '@angular/forms'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { ToastrModule } from "ngx-toastr"
import { RegisterComponent } from './component/register/register.component';
import { HomeComponent } from './component/home/home.component';
import { AdminComponent } from './component/admin/admin.component';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button'
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { MatListModule } from '@angular/material/list';
import { AboutComponent } from './component/about/about.component';
import { ContactComponent } from './component/contact/contact.component';
import { AccountComponent } from './component/account/account.component';
import { HttpAuthInterceptor } from './interceptors/http-auth.interceptor';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmUserActionDialogComponent } from './component/common/confirm-user-action-dialog/confirm-user-action-dialog.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { UserDialogComponent } from './component/admin/component/user-dialog/user-dialog.component';
import { AddressComponent } from './component/account/component/address/address.component';
import { AddressFormComponent } from './component/account/component/address/component/address-form/address-form.component';
import { UpdateInfoFormComponent } from './component/account/component/update-info-form/update-info-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    NavBarComponent,
    AboutComponent,
    ContactComponent,
    AccountComponent,
    ConfirmUserActionDialogComponent,
    UserDialogComponent,
    AddressComponent,
    AddressFormComponent,
    UpdateInfoFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    MatButtonModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatExpansionModule,
    MatTooltipModule,
    MatDialogModule,
    MatSelectModule,
    MatFormFieldModule,
    FormsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
