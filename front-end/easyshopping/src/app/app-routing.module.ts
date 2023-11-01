import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './component/admin/admin.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { userAuthGuard } from './guard/userAuth.guard';
import { adminAuthGuard } from './guard/adminAuth.guard';
import { anonAuthGuard } from './guard/anonAuth.guard';
import { AccountComponent } from './component/account/account.component';
import { AboutComponent } from './component/about/about.component';
import { ContactComponent } from './component/contact/contact.component';
import { CartComponent } from './component/cart/cart.component';
import { AddressComponent } from './component/account/component/address/address.component';
import { UpdateInfoFormComponent } from './component/account/component/update-info-form/update-info-form.component';
import { ChangePasswordComponent } from './component/account/component/change-password/change-password.component';
import { CreateListingComponent } from './component/account/component/create-listing/create-listing/create-listing.component';

const routes: Routes = [
  { path: '', component: HomeComponent, },
  { path: 'login', component: LoginComponent, canActivate: [anonAuthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [anonAuthGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [adminAuthGuard] },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [userAuthGuard],
    children: [
      {
        path: 'addresses',
        component: AddressComponent,
      },
      {
        path: 'update-info/:id',
        component: UpdateInfoFormComponent,
      },
      {
        path: 'change-password/:id',
        component: ChangePasswordComponent,
      },
      {
        path: 'create-listing/:id',
        component: CreateListingComponent,
      }

    ]
  },
  { path: 'cart', component: CartComponent, canActivate: [userAuthGuard] },
  { path: 'about', component: AboutComponent, },
  { path: 'contact', component: ContactComponent, },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
