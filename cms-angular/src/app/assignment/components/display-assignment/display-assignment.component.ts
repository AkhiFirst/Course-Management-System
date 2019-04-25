import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/course/services/course.service';
import { Course } from 'src/app/course/model/course';
import { AssignmentService } from '../../services/assignment.service';
import { Assignment } from '../../model/assignment';

@Component({
  selector: 'app-display-assignment',
  templateUrl: './display-assignment.component.html',
  styleUrls: ['./display-assignment.component.scss']
})
export class DisplayAssignmentComponent implements OnInit {
  title: String;
  instructorId: Number;
  fileNames: String[];
  assignmentArray: Assignment[]
  srcPath: String ='F:\Akhila project\Files\\';
  role: String;
  constructor(private assignmentService: AssignmentService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
      this.instructorId = params.instructorId;
      this.role = params.role;
    });
    if(this.role == 'student') {
        this.getAssignementFilesForStudent(this.title,this.instructorId);
    } else if(this.role == 'instructor') {
      this.getAssignementFilesForInstructor(this.title,this.instructorId);
    }
    this.srcPath = this.srcPath+'Courses\\'+this.instructorId+this.title;
  }
  getAssignementFilesForStudent(courseName: String,instructorId:Number) : any {
    this.assignmentService.getAssignementFilesForStudent(courseName,instructorId).subscribe(resp => {
      console.log("Files resp::"+JSON.stringify(resp.json()));  
      this.assignmentArray = resp.json();
      });;
  }
  getAssignementFilesForInstructor(courseName: String,instructorId:Number) : any {
    this.assignmentService.getAssignementFilesForInstructor(courseName,instructorId).subscribe(resp => {
      console.log("Files resp::"+JSON.stringify(resp.json()));  
      this.assignmentArray = resp.json();
      });;
  }
  downloadAssignmetFile(assignment: Assignment) {
    this.assignmentService.downloadAssignmetFile(assignment).subscribe(data => {
      console.log("Files resp::"+JSON.stringify(data));  
      const blob = new Blob([data], { type: 'application/pdf' });
      if (navigator.msSaveBlob) 
      { 
          // IE 10+
          navigator.msSaveBlob(blob, assignment.fileName);
      }
      else 
      {
          let link = document.createElement("a");
          if (link.download !== undefined) 
          {
              let url = URL.createObjectURL(blob);
              link.setAttribute("href", url);
              link.setAttribute("download", assignment.fileName);
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
      });
    }

}
