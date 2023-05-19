import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCompensationComponent } from './create-compensation.component';

describe('CreateCompensationComponent', () => {
  let component: CreateCompensationComponent;
  let fixture: ComponentFixture<CreateCompensationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCompensationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCompensationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
