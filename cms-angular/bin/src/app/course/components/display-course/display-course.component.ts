import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Course } from '../../model/course';
import { Router } from '@angular/router';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-display-course',
  templateUrl: './display-course.component.html',
  styleUrls: ['./display-course.component.scss']
})
export class DisplayCourseComponent implements OnInit {
  user: User;
  courseArray: Course[];
  course: Course;
  constructor(private courseService :  CourseService, private router: Router) { }

  ngOnInit() {
    this.user= JSON.parse(localStorage.getItem('user'));
    this.getCourses();
  }
  getCourses() {
    this.courseService.getCourses(this.user).subscribe(resp => {
      console.log("resp::"+JSON.stringify(resp.json()));
       
      this.courseArray =<Course[]>resp.json();
      });
  }
  // navigateToHome(course: Course) {
  //   this.router.navigate(['home',{title : course.title, instructorId: course.instructorId}]);
  // }
  //

}
