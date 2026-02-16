import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { HomeComponent } from './home/home.component';
import { RecruiterSearchComponent } from './recruiter/recruiter-search.component';
import { AdminUsersComponent } from './admin/admin-users.component';

export const appRoutes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'recruiter', component: RecruiterSearchComponent },
  { path: 'admin', component: AdminUsersComponent }
];
