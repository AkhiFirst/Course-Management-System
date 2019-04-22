import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { DisplayCourseComponent } from './components/display-course/display-course.component';
import { HomeComponent } from './components/home/home.component';
import { CourseContentComponent } from './components/course-content/course-content.component';
import { AddAssignmentComponent } from '../assignment/components/add-assignment/add-assignment.component';

const routes: Routes = [{path: 'addcourse', component:AddCourseComponent}, 
                        {path: 'displaycourse', component:DisplayCourseComponent},
                      {path:'home', component:HomeComponent, children: [{path:'coursecontentcomponent', component:CourseContentComponent, outlet:'outlet1'},
                      {path:'AddAssignmentComponent', component: AddAssignmentComponent, outlet:'outlet1'},{path:'addcoursecomponent', component: AddCourseComponent, outlet:'outlet1'}]},
                      ];
                      

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CourseRoutingModule { }
