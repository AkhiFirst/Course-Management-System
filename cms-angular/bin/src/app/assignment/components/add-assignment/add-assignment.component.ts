import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { environment } from 'bin/src/environments/environment';

@Component({
  selector: 'app-add-assignment',
  templateUrl: './add-assignment.component.html',
  styleUrls: ['./add-assignment.component.scss']
})
export class AddAssignmentComponent implements OnInit {

  constructor(private http: Http) { }

  ngOnInit() {
  }

  fileChange(event: any, input: any) {
    let fileList: FileList = event.target.files;
    let formData:FormData = new FormData();
    for(let i=0; i<fileList.length; i++) {
      let file: File = fileList[i];
      formData.append('uploadFile', file, file.name);
    }
    let headers = new Headers();
        headers.append('Accept', 'application/json');
        let options = new RequestOptions({ headers: headers });
        this.http.post(environment.api+'/courseManagementSysController/addAssignemnt', formData, options).subscribe(resp => {let msg = resp.json();console.log("msg::"+msg);});
}

}
