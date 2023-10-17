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
    MatListModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
