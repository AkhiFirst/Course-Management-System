import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssignmentRoutingModule } from './assignment-routing.module';
import { AddAssignmentComponent } from './components/add-assignment/add-assignment.component';
import { MatCardModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { DisplayAssignmentComponent } from './components/display-assignment/display-assignment.component';
import { AddAssignmentInstructorComponent } from './components/add-assignment-instructor/add-assignment-instructor.component';

@NgModule({
  declarations: [AddAssignmentComponent, DisplayAssignmentComponent, AddAssignmentInstructorComponent],
  imports: [
    CommonModule,
    AssignmentRoutingModule,
    MatCardModule,
    BrowserModule 
  ]
})
export class AssignmentModule { }
