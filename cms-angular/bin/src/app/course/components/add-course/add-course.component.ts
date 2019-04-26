import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CourseService } from '../../services/course.service';
import { User } from 'src/app/user/model/user';
import { Course } from '../../model/course';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.scss']
})
export class AddCourseComponent implements OnInit {
addCoursesForm: FormGroup;
user: User;
courses: Array<Course>;
submitted = false;
registeredCourse: number;
constructor(private formBuilder: FormBuilder, private courseService: CourseService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user= JSON.parse(localStorage.getItem('user'));
   // this.user=this.userService.getSession('user');
    // this.route.queryParams.subscribe(params => {
    //   this.user.username= params.username;
    //   this.user.deptName = params.deptName;
    // });
    this.addCoursesForm = this.formBuilder.group({ course_id: ['', Validators.required]});
    this.fetchCourses();
  }
  get f() { return this.addCoursesForm.controls;}
  fetchCourses(){
      this.courseService.fetchAddCourses(this.user).subscribe(resp => {
        this.courses = <Course[]>resp.json();
        console.log("resp::" + JSON.stringify(resp.json()));
      });
  }
  onSubmit() {
    this.submitted=true;
    if (this.addCoursesForm.invalid)
      return;
    this.courseService.registerCourse(this.user, this.addCoursesForm.value).subscribe(resp => {
      let flag = resp.json(); console.log(JSON.stringify(resp.json)) ;
      this.registeredCourse=flag;
      console.log(this.registeredCourse);
      if (this.registeredCourse == 1) {
        this.courseService.sendSubmitMessage("course added");
        alert("You have registered for the course");
        this.router.navigate(['displaycourse']);
      }
      else if(this.registeredCourse == 2){
        this.courseService.sendSubmitMessage("Courses exceeded");
        alert("You have reached maximum course limit for this semester");
      }
      else if(this.registeredCourse == 3){
        this.courseService.sendSubmitMessage("Already registered");
        alert("You have already registered for this course");
      }
      else {
        this.courseService.sendSubmitMessage("Error occured");
        alert("Unexpected error");
      }
    });
}
}

