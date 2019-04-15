import { Injectable, EventEmitter } from '@angular/core';
import { Http, RequestOptionsArgs, Headers } from '@angular/http';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public submitMessage: EventEmitter<any> = new EventEmitter();
  registrationUrl = environment.api+'/courseManagementSysController/getStudentDetails';
  constructor(private http: Http) { }

  register(user: User) : any {
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
    return this.http.post(this.registrationUrl, user, httpHeaderOptions);
  }
  public sendSubmitMessage(msg: String) {
    console.log("success::"+msg);
    this.submitMessage.next(msg);
  }
}
