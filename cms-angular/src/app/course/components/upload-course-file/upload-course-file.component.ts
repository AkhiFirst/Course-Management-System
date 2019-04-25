import { Component, OnInit } from '@angular/core';
import { Course } from '../../model/course';
import { Http, RequestOptions, Headers } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-upload-course-file',
  templateUrl: './upload-course-file.component.html',
  styleUrls: ['./upload-course-file.component.scss']
})
export class UploadCourseFileComponent implements OnInit {
  course:Course = new Course();
  constructor(private http: Http, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.course.title= params.title;
    });
  }
  uploadCourse(event: any, input: any) {
    let fileList: FileList = event.target.files;
    let formData:FormData = new FormData();
    for(let i=0; i<fileList.length; i++) {
      let file: File = fileList[i];
      formData.append('uploadFile', file, file.name);
    }
    let headers = new Headers();
        headers.append('Accept', 'application/json');
        let options = new RequestOptions({ headers: headers });
        this.http.post(environment.api+'/courseManagementSysController/uploadcourse/'+this.course.title, formData, options).subscribe(resp => {let msg = resp.json();console.log("msg::"+msg);});
}

}
