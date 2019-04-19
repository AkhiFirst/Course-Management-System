import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AssignmentRoutingModule } from './assignment-routing.module';
import { AddAssignmentComponent } from './components/add-assignment/add-assignment.component';
import { MatCardModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  declarations: [AddAssignmentComponent],
  imports: [
    CommonModule,
    AssignmentRoutingModule,
    MatCardModule,
    BrowserModule 
  ]
})
export class AssignmentModule { }
