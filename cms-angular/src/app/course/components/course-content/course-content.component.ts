import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course-content',
  templateUrl: './course-content.component.html',
  styleUrls: ['./course-content.component.scss']
})
export class CourseContentComponent implements OnInit {
  title: String;
  instructorId: Number;
  fileNames: String[];
  srcPath: String ='F:\Akhila project\Files\\';

  constructor(private courseService: CourseService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
      this.instructorId = params.instructorId;
    });
    this.getCourseRelatedFiles(this.title,this.instructorId);
    this.srcPath = this.srcPath+'Courses\\'+this.instructorId+this.title;
  }
  getCourseRelatedFiles(courseName: String,instructorId:Number) : any {
    this.courseService.getCourseRelatedFiles(courseName,instructorId).subscribe(resp => {
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
