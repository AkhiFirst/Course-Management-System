import { Injectable } from '@angular/core';
import { Course } from 'src/app/course/model/course';
import { Http, Headers } from '@angular/http';
import { environment } from 'src/environments/environment';
import { Assignment } from '../model/assignment';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {
  downloadAssignmetFileUrl = environment.api+'/courseManagementSysController/downloadassignmetfile';
  getAssignementFilesForInsUrl = environment.api+'/courseManagementSysController/getassignementfilesforinstructor';
  getAssignementFilesForStdUrl = environment.api+'/courseManagementSysController/getAssignementfilesforstudent';
  course:Course = new Course();
  
  constructor(private http: Http) { }
  getAssignementFilesForInstructor(courseName: String,id:String) : any {
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
  this.course.id = id;
  this.course.type = "Assignments";
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.getAssignementFilesForInsUrl, this.course, httpHeaderOptions);
  }
  getAssignementFilesForStudent(courseName: String,id: String) : any {
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
  this.course.id = id;
  this.course.type = "Assignments";
    //return this.http.post(this.registrationUrl, user, httpHeaderOptions);
    return this.http.post(this.getAssignementFilesForStdUrl, this.course, httpHeaderOptions);
  }
  downloadAssignmetFile(assignment: Assignment) : any {
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
    return this.http.post(this.downloadAssignmetFileUrl, assignment, httpHeaderOptions);
  }
}
