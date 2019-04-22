import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, OutletContext } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { Course } from '../../model/course';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  navPosistion: String = 'end';
  title: String;
  instructorId: Number;
  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
      this.instructorId = params.instructorId;
    });
  }

  

}
