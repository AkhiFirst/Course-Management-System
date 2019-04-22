import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Http, Headers } from '@angular/http';
import { Course } from '../model/course';
import { User } from 'src/app/user/model/user';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  getSemCourseUrl = environment.api+'/courseManagementSysController/getcurrentsemcourses';
  getAllFilesUrl = environment.api+'/courseManagementSysController/getallfiles';
  downloadFileUrl = environment.api+'/courseManagementSysController/getfile';
  course:Course = new Course();

  constructor(private http: Http) { }

  getCurrentSemCourses(user: User) : any {
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
    return this.http.post(this.getSemCourseUrl, user, httpHeaderOptions);
  }

  getAllFiles(courseName: String,instructorId:Number) : any {
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
  this.course.title = courseName;
  this.course.instructorId = instructorId;
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.getAllFilesUrl, this.course, httpHeaderOptions);
  }
  downloadPdf(courseName: String,instructorId:Number,fileName:String) : any {
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
  this.course.title = courseName;
  this.course.instructorId = instructorId;
  this.course.courseFileName = fileName;
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.downloadFileUrl, this.course, httpHeaderOptions);
  }
}
