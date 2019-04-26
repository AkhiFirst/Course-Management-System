import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/user/model/user';

@Component({
  selector: 'app-add-assignment-instructor',
  templateUrl: './add-assignment-instructor.component.html',
  styleUrls: ['./add-assignment-instructor.component.scss']
})
export class AddAssignmentInstructorComponent implements OnInit {
  title: String;
  constructor(private http: Http, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
    });
  }
  uploadAssignmentByInstructor(event: any, input: any) {
    let fileList: FileList = event.target.files;
    console.log("FileList"+fileList.length);
    let formData:FormData = new FormData();
    for(let i=0; i<fileList.length; i++) {
      let file: File = fileList[i];
      formData.append('uploadFile', file, file.name);
    }
    let headers = new Headers();
  headers.append('Accept', 'application/json');
  let options = new RequestOptions({ headers: headers });
  this.http.post(environment.api+'/courseManagementSysController/uploadassignmentbyinstructor/'+this.title, formData, options).subscribe(resp => {let msg = resp.json();console.log("msg::"+msg);});
  alert("Successfully uploaded");
}
    
}
