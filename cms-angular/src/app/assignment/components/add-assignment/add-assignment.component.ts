import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { environment } from 'bin/src/environments/environment';
import { ActivatedRoute } from '@angular/router';
import { User } from 'bin/src/app/user/model/user';

@Component({
  selector: 'app-add-assignment',
  templateUrl: './add-assignment.component.html',
  styleUrls: ['./add-assignment.component.scss']
})
export class AddAssignmentComponent implements OnInit {
  title: String;
  user: User;
  id: String;
  constructor(private http: Http, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user= JSON.parse(localStorage.getItem('user'));
    this.id=this.user.id;
    this.route.queryParams.subscribe(params => {
      this.title= params.title;
    });
  }

  uploadAssignment(event: any, input: any) {
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
        this.http.post(environment.api+'/courseManagementSysController/addassignemnt/'+this.title+'/'+this.id, formData, options).subscribe(resp => {let msg = resp.json();console.log("msg::"+msg);});
}

}
