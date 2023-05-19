import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserlistComponent } from './userlist/userlist.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { CompensationReportComponent } from './compensation-report/compensation-report.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './_auth/auth.guard';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { UserService } from './_services/user.service';
import { CreateCompensationComponent } from './create-compensation/create-compensation.component';
import { SuccessMessageComponent } from './success-message/success-message.component';
import { FailureMessageComponent } from './failure-message/failure-message.component';

@NgModule({
  declarations: [
    AppComponent,
    ForbiddenComponent,
    NavbarComponent,
    HomeComponent,
    SignInComponent,
    UserlistComponent,
    UserDetailsComponent,
    MyProfileComponent,
    CompensationReportComponent,
    CreateCompensationComponent,
    SuccessMessageComponent,
    FailureMessageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
