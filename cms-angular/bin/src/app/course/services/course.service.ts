import { Injectable, EventEmitter } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Http, Headers, ResponseContentType } from '@angular/http';
import { Course } from '../model/course';
import { User } from 'src/app/user/model/user';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  public submitMessage: EventEmitter<any> = new EventEmitter();
  getCoursesUrl = environment.api+'/courseManagementSysController/getcourses';
  getCourseRelatedFilesUrl = environment.api+'/courseManagementSysController/getcourserelatedfiles';
  downloadCourseFileUrl = environment.api+'/courseManagementSysController/downloadcoursefile';
  fetchAddCoursesUrl = environment.api+'/courseManagementSysController/getAddCourse';
  registerCourseUrl= environment.api+'/courseManagementSysController/registerCourse';
  course:Course = new Course();
  user: User;

  constructor(private http: Http) { }

  getCourses(user: User) : any {
    const httpHeaderOptions  = 
  {
    headers: new Headers({
    'Content-Type' : 'application/json',
    'Accept'       : 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
    })
  };
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.getCoursesUrl, user, httpHeaderOptions);
  }

  getCourseRelatedFiles(title: String,id:String) : any {
    const httpHeaderOptions  = 
  {
    headers: new Headers({
    'Content-Type' : 'application/json',
    'Accept'       : 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
    })
  };
  this.course.title=title;
  this.course.type = "Courses";
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.getCourseRelatedFilesUrl, this.course, httpHeaderOptions);
  }
  downloadCourseFile(courseName: String,fileName:String) : any {
    const httpHeaderOptions  = 
  {
    'responseType'  : ResponseContentType.Blob,
    headers: new Headers({
    'Content-Type' : 'application/json',
    'Accept'       : 'application/pdf',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
    })
    
  };
  this.course.type = 'Courses';
  this.course.title = courseName;
  this.course.courseFileName = fileName;
  console.log("course:::"+JSON.stringify(this.course));
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.downloadCourseFileUrl, this.course, httpHeaderOptions);
  }
  fetchAddCourses(user: User): any {
    const httpHeaderOptions =
    {
      headers: new Headers({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
      })
    };
    return this.http.post(this.fetchAddCoursesUrl, user, httpHeaderOptions);
  }
  registerCourse(user: User, course: Course): any{
    const httpHeaderOptions =
    {
      headers: new Headers({
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'POST, GET, OPTIONS, PUT, DELETE',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, Accept, Authorization, X- Request-With'
      })
    };
    return this.http.post(this.registerCourseUrl, {user: user, course:course}, httpHeaderOptions);
  }
  public sendSubmitMessage(msg: String) {
    console.log(msg);
    this.submitMessage.next(msg);

  }
}
