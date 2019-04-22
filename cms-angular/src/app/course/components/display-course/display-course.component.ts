import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from '../../model/course';
import { User } from 'bin/src/app/user/model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-display-course',
  templateUrl: './display-course.component.html',
  styleUrls: ['./display-course.component.scss']
})
export class DisplayCourseComponent implements OnInit {
  user: User  = new User();
  courseArray: Course[];
  course: Course;
  constructor(private courseService :  CourseService, private router: Router) { }

  ngOnInit() {
    this.user.id = '1131700';
    this.getCurrentSemCourses();
  }
  getCurrentSemCourses() {
    this.courseService.getCurrentSemCourses(this.user).subscribe(resp => {
      console.log("resp::"+JSON.stringify(resp.json()));
        let data = resp.json()   
      this.courseArray = data;
      });
  }
  // navigateToHome(course: Course) {
  //   this.router.navigate(['home',{title : course.title, instructorId: course.instructorId}]);
  // }
  //

}
