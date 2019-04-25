import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddAssignmentComponent } from './components/add-assignment/add-assignment.component';

// const routes: Routes = [{path:'AddAssignmentComponent', component: AddAssignmentComponent}];
const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignmentRoutingModule { }
