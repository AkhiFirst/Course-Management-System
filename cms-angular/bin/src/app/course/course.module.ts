import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CourseRoutingModule } from './course-routing.module';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { DisplayCourseComponent } from './components/display-course/display-course.component';
import { IgxCardModule } from 'igniteui-angular';
import { MatCardModule, MatSidenavModule, MatListModule } from '@angular/material';
import { HomeComponent } from './components/home/home.component';
import { CourseContentComponent } from './components/course-content/course-content.component';
import { CourseService } from './services/course.service';
import {MatGridListModule} from '@angular/material/grid-list';
import { PdfViewerComponent } from 'ng2-pdf-viewer';
import { UploadCourseFileComponent } from './components/upload-course-file/upload-course-file.component'; 
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [AddCourseComponent, DisplayCourseComponent, HomeComponent, CourseContentComponent,PdfViewerComponent, UploadCourseFileComponent],
  imports: [
    CommonModule,
    CourseRoutingModule,
    IgxCardModule,
    MatCardModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    ReactiveFormsModule
  ],
  exports: [HomeComponent, DisplayCourseComponent, AddCourseComponent],
  providers: [
    CourseService
  ]
})
export class CourseModule { }
