import { LoginServiceService } from './login-service.service';
import { Component, OnInit, Pipe } from '@angular/core';
import { Student } from "./student";
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  students: Observable<Student[]>;
  constructor(private data: LoginServiceService) { }

  ngOnInit() {
    this.reloadData();
    }
    reloadData() {
      this.students=this.data.getStudentDetails();
      //.subscribe(students => this.students = students);
    }
}
