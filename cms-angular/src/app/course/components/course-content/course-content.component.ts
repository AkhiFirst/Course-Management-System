import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-course-content',
  templateUrl: './course-content.component.html',
  styleUrls: ['./course-content.component.scss']
})
export class CourseContentComponent implements OnInit {
  title: String;
  user: User;
  fileNames: String[];
  srcPath: String ='C:\Users\yarla\Documents\Akhila Project\Files\\';

  constructor(private courseService: CourseService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user= JSON.parse(localStorage.getItem('user'));
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
    });
    this.getCourseRelatedFiles(this.title,this.user.id);
    this.srcPath = this.srcPath+'Courses\\'+this.user.id+this.title;
  }
  getCourseRelatedFiles(title: String,id:String) : any {
    this.courseService.getCourseRelatedFiles(title,id).subscribe(resp => {
      console.log("Files resp::"+JSON.stringify(resp.json()));  
      this.fileNames = resp.json();
      });;
  }
  downloadCourseFile(fileName: string) {
    this.courseService.downloadCourseFile(this.title,fileName).subscribe(data => {
      console.log("Files resp::"+JSON.stringify(data));  
      const blob = new Blob([data], { type: 'application/pdf' });
      if (navigator.msSaveBlob) 
      { 
          
          navigator.msSaveBlob(blob, fileName);
      }
      else 
      {
          let link = document.createElement("a");
          if (link.download !== undefined) 
          {
              let url = URL.createObjectURL(blob);
              link.setAttribute("href", url);
              link.setAttribute("download", fileName);
              link.style.visibility = 'hidden';
              document.body.appendChild(link);
              link.click();
              document.body.removeChild(link);
          }
          else
          {
              //html5 download not supported
          }
      }   
      });;
    }
}
