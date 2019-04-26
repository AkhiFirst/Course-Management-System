import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from 'src/app/course/services/course.service';
import { Course } from 'src/app/course/model/course';
import { AssignmentService } from '../../services/assignment.service';
import { Assignment } from '../../model/assignment';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-display-assignment',
  templateUrl: './display-assignment.component.html',
  styleUrls: ['./display-assignment.component.scss']
})
export class DisplayAssignmentComponent implements OnInit {
  title: String;
  fileNames: String[];
  assignmentArray: Assignment[]
  srcPath: String ='C:\Users\yarla\Documents\Akhila Project\Files\\';
  user: User
  constructor(private assignmentService: AssignmentService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user= JSON.parse(localStorage.getItem('user'));
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
    });
    if(this.user.role_id == 2) {
        this.getAssignementFilesForStudent(this.title,this.user.id);
    } else if(this.user.role_id == 1) {
      this.getAssignementFilesForInstructor(this.title,this.user.id);
    }
    this.srcPath = this.srcPath+'Courses\\'+this.user.id+this.title;
  }
  getAssignementFilesForStudent(courseName: String,id: String) : any {
    this.assignmentService.getAssignementFilesForStudent(courseName,id).subscribe(resp => {
      console.log("Files resp::"+JSON.stringify(resp.json()));  
      this.assignmentArray = resp.json();
      });;
  }
  getAssignementFilesForInstructor(courseName: String,id:String) : any {
    this.assignmentService.getAssignementFilesForInstructor(courseName,id).subscribe(resp => {
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
