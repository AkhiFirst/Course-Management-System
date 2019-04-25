import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, OutletContext } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Course } from '../../model/course';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
user: User;
  navPosistion: String = 'end';
  title: String;
  instructorId: Number;
  role : String;
  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.route.queryParams.subscribe(params => {
      this.title= params.title;
      this.instructorId = params.instructorId;
      this.role = params.role;
    });
  }

  

}
