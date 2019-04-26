import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { DisplayCourseComponent } from './components/display-course/display-course.component';
import { HomeComponent } from './components/home/home.component';
import { CourseContentComponent } from './components/course-content/course-content.component';
import { AddAssignmentComponent } from '../assignment/components/add-assignment/add-assignment.component';
import { UploadCourseFileComponent } from './components/upload-course-file/upload-course-file.component';
import { DisplayAssignmentComponent } from '../assignment/components/display-assignment/display-assignment.component';
import { AddAssignmentInstructorComponent } from '../assignment/components/add-assignment-instructor/add-assignment-instructor.component';
const routes: Routes = [{path: 'addcourse', component:AddCourseComponent}, 
                        {path: 'displaycourse', component:DisplayCourseComponent},
                      {path:'home', component:HomeComponent, children: [{path:'coursecontentcomponent', component:CourseContentComponent, outlet:'outlet1'},
                      {path:'AddAssignmentComponent', component: AddAssignmentComponent, outlet:'outlet1'},{path:'addcoursecomponent', component: AddCourseComponent, outlet:'outlet1'},
                      {path:'uploadcoursefilecomponent', component: UploadCourseFileComponent, outlet:'outlet1'}, {path:'displayassignmentcomponent', component: DisplayAssignmentComponent, outlet:'outlet1'},
                      {path:'addassignmentinstructorcomponent', component: AddAssignmentInstructorComponent, outlet:'outlet1'}]}
                      ];
                      

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CourseRoutingModule { }
