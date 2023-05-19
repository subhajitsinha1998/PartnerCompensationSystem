import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompensationReportComponent } from './compensation-report.component';

describe('CompensationReportComponent', () => {
  let component: CompensationReportComponent;
  let fixture: ComponentFixture<CompensationReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompensationReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompensationReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
