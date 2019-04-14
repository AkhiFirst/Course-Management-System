import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({ 
  providedIn: 'root'
})
export class LoginServiceService {

  private baseUrl = 'http://localhost:8080/api/studentDetails';
  private httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin':'*',
      'Content-Type':  'application/json',
      'Authorization': 'Basic my-auth-token'
    })
  };
  // private headers = new HttpHeaders({Authorization: 'Basic ' + btoa('test:test123')});
  constructor(private http: HttpClient) { }

  getStudentDetails(): Observable<any> {
//const headers = new HttpHeaders({Authorization: 'Basic ' + btoa('username:password')});
return this.http.get(this.baseUrl, this.httpOptions);
  }
}
