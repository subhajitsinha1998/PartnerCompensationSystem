import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  signInForm!: FormGroup;
  forceResetPasswordForm!: FormGroup;
  firstSignIn: boolean = false
  session!: string
  wrongCredentials: boolean = false

  ngOnInit(): void {
    this.signInForm = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, Validators.required)
    });
    this.forceResetPasswordForm = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'oldPassword': new FormControl(null, Validators.required),
      'newPassword': new FormControl(null, [Validators.required, this.validPassword]),
      'confirmPassword': new FormControl(null, [Validators.required])
    })
  }

  isFirstSignIn(): boolean {
    return this.firstSignIn
  }

  forceResetPassword() {
    let forceResetPasswordRequest = this.forceResetPasswordForm.value;
    delete forceResetPasswordRequest.confirmPassword
    forceResetPasswordRequest.session = this.session
    this.userService.forceResetPassword(forceResetPasswordRequest).subscribe(
      (response: any) => {
        this.loginSuccess(response)
      },
      (error) => {
        
        console.log(error)
      }
    )
  }

  signIn() {
    if (this.signInForm && this.signInForm.valid) {
      this.userService.signIn(this.signInForm.value).subscribe(
        (response: any) => {
          if (response.firstSignIn) {
            this.firstSignIn = response.firstSignIn
            this.session = response.session
          }
          else {
            this.loginSuccess(response)
          }
        },
        (error) => {
          this.wrongCredentials = true
          console.log(error)
        }
      )
    }
  }

  loginSuccess(signInResponse: any) {
    this.userAuthService.setUserDetails(signInResponse.user)
    this.userAuthService.setToken(signInResponse.accessToken)
    this.router.navigate(['/'])
  }

  validPassword(control: AbstractControl): { [s: string]: boolean } | null {
    var validatorRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
    if (!control.value || !control.value.match(validatorRegex)) {
      return { 'InvalidPassword': true };
    }
    return null;
  }

  showValidationError(forInput: string) {
    return !this.forceResetPasswordForm.get(forInput)?.valid && this.forceResetPasswordForm.get(forInput)?.touched;
  }
  
}
