import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAssignmentInstructorComponent } from './add-assignment-instructor.component';

describe('AddAssignmentInstructorComponent', () => {
  let component: AddAssignmentInstructorComponent;
  let fixture: ComponentFixture<AddAssignmentInstructorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAssignmentInstructorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAssignmentInstructorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
