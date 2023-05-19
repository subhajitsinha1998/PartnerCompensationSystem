import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompensationReportComponent } from './compensation-report/compensation-report.component';
import { CreateCompensationComponent } from './create-compensation/create-compensation.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserlistComponent } from './userlist/userlist.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  {path: 'sign-in', component: SignInComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard], data: {roles:['admin_user', 'compensation_user', 'report_user']}},
  {path: 'myprofile', component: MyProfileComponent, canActivate: [AuthGuard], data: {roles:['admin_user', 'compensation_user', 'report_user']}},
  {path: 'users', component: UserlistComponent, canActivate: [AuthGuard], data: {roles:['admin_user']}},
  {path: 'create-user', component: UserDetailsComponent, canActivate: [AuthGuard], data: {roles:['admin_user']}},
  {path: 'compensation-report', component: CompensationReportComponent, canActivate: [AuthGuard], data: {roles:['report_user']}},
  {path: 'create-compensation', component: CreateCompensationComponent, canActivate: [AuthGuard], data: {roles:['compensation_user']}},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
