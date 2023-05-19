import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Compensation } from '../_models/commpensation';
import { CompensationService } from '../_services/compensation.service';

@Component({
  selector: 'app-create-compensation',
  templateUrl: './create-compensation.component.html',
  styleUrls: ['./create-compensation.component.css']
})
export class CreateCompensationComponent implements OnInit {

  constructor (
    private compensationService: CompensationService
  ) { }

  compensationPlanForm: FormGroup

  ngOnInit(): void {
    this.compensationPlanForm = new FormGroup({
      'partnerName': new FormControl(null, [Validators.required]),
      'planName': new FormControl(null, [Validators.required]),
      'planType': new FormControl('volume', [Validators.required]),
      'minPercent': new FormControl(null, [Validators.required, this.validPercent]),
      'maxPercent': new FormControl(null, [Validators.required, this.validPercent]),
      'startDate': new FormControl(null, [Validators.required]),
      'endDate': new FormControl(null, [Validators.required, ]),
    },
    { validators: [this.validEndDate] })
  }

  validPercent(control: AbstractControl): { [s: string]: boolean } | null {
    if (!(control.value && control.value > 0 && control.value < 100)) return { 'InvalidPercent': true }
    return null
  }

  validEndDate (control: AbstractControl): { [s: string]: boolean } | null {
    let endDate = new Date(
      control.get('endDate')?.value?.year,
      control.get('endDate')?.value?.month,
      control.get('endDate')?.value?.day
    )
    let startDate = new Date(
      control.get('startDate')?.value?.year,
      control.get('startDate')?.value?.month,
      control.get('startDate')?.value?.day
    ) 
    if (startDate > endDate) {
      control.get('startDate')?.setErrors({'InvalidDate': true})
      control.get('endDate')?.setErrors({'InvalidDate': true})
      return { 'InvalidDate': true }
    } 
    control.get('startDate')?.setErrors(null)
    control.get('endDate')?.setErrors(null)
    return null
  }

  showValidationError(forInput: string) {
    return !this.compensationPlanForm.get(forInput)?.valid && this.compensationPlanForm.get(forInput)?.touched;
  }

  clear(){
    this.compensationPlanForm.reset()
    this.compensationPlanForm.get('planType')?.setValue('volume')
  }

  toCompensation(obj: any) {
    let compensation = new Compensation()
    compensation.planName = obj.planName
    compensation.partnerName = obj.partnerName
    compensation.startDate = `${obj.startDate.year}-${obj.startDate.month}-${obj.startDate.day}`
    compensation.endDate = `${obj.endDate.year}-${obj.endDate.month}-${obj.endDate.day}`
    compensation.planType = obj.planType
    compensation.maxPercent = obj.maxPercent
    compensation.minPercent = obj.minPercent
    return compensation
  }

  createCompensation() {
    let compensation: Compensation = this.toCompensation(this.compensationPlanForm.value);
    this.clear()
    this.compensationService.createCompensation(compensation).subscribe(
      (response) => {
        console.log(response)
      },
      (error) => {
        console.log(error.error)
      }
    )
  }

}

