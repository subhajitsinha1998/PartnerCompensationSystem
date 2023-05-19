import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  constructor(
    private userService: UserService
  ) { }

  userDetailsForm!: FormGroup;
  showError: boolean = false
  showSuccess: boolean = false
  employeeId: number

  ngOnInit(): void {
    this.userDetailsForm = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'firstName': new FormControl(null, [Validators.required]),
      'lastName': new FormControl(null),
      'location': new FormControl(null),
      'jobTitle': new FormControl(null),
      'department': new FormControl(null),
      'role': new FormControl('compensation_user')
    })
  }

  showValidationError(forInput: string) {
    return !this.userDetailsForm.get(forInput)?.valid && this.userDetailsForm.get(forInput)?.touched;
  }

  clearForm() {
    this.userDetailsForm.reset()
    this.userDetailsForm.get('role')?.setValue('compensation_user')
    this.showError = false
    this.showSuccess = false
    this.employeeId = NaN
  }

  saveUser() {
    this.userService.createUser(this.userDetailsForm.value).subscribe(
      (response: any) => {
        this.clearForm()
        this.showSuccess = true
        this.employeeId = response.employeeId
      },
      (error) => {
        this.showError = true
        console.log(error)
      }
    )
  }

}
