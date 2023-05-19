import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Compensation } from '../_models/commpensation';
import { CompensationService } from '../_services/compensation.service';
import * as XLSX from 'xlsx';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-compensation-report',
  templateUrl: './compensation-report.component.html',
  styleUrls: ['./compensation-report.component.css']
})
export class CompensationReportComponent implements OnInit {

  constructor (
    private compensationService: CompensationService
  ) { }

  compensationPlans: Array<Compensation>
  filename= environment.COMPENSATION_REPORT_FILE_NAME
  @ViewChild('compensationReport') compensationReport: ElementRef

  ngOnInit(): void {
    this.compensationService.getAllCompensation().subscribe(
      (response: any) => {
        this.compensationPlans = response['content']
      },
      (error) => {
        console.log(error.error)
      }
    )
  }

  downloadReport(): void
  {
    const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(this.compensationReport.nativeElement);
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
    XLSX.writeFile(wb, this.filename);
  }

}
