import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './component/admin/admin.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { userAuthGuard } from './guard/userAuth.guard';
import { adminAuthGuard } from './guard/adminAuth.guard';
import { anonAuthGuard } from './guard/anonAuth.guard';
import { AccountComponent } from './component/account/account.component';
import { AboutComponent } from './component/about/about.component';
import { ContactComponent } from './component/contact/contact.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [userAuthGuard] },
  { path: 'login', component: LoginComponent, canActivate: [anonAuthGuard] },
  { path: 'register', component: RegisterComponent, canActivate: [anonAuthGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [adminAuthGuard] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [userAuthGuard] },
  { path: 'account', component: AccountComponent, canActivate: [userAuthGuard] },
  { path: 'about', component: AboutComponent, },
  { path: 'contact', component: ContactComponent, }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
