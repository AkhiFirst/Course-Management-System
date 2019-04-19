import { Injectable, EventEmitter } from '@angular/core';
import { Http, RequestOptionsArgs, Headers } from '@angular/http';
import { User } from '../model/user';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  public submitMessage: EventEmitter<any> = new EventEmitter();
  registrationUrl = environment.api+'/courseManagementSysController/register';
  loginUrl = environment.api+'/courseManagementSysController/login';
  constructor(private http: Http,
              private router: Router) { 
  }

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
  login(user: User) : any {
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
    return this.http.post(this.loginUrl, user, httpHeaderOptions);
  }
  public sendSubmitMessage(msg: String) {
    console.log(msg);
    this.submitMessage.next(msg);
    //this.router.navigateByUrl("/register");

  }
}
