import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  user: User;

  constructor(private formBuilder: FormBuilder, private userService: UserService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({ firstName: ['', Validators.required], lastName: ['', Validators.required], username: ['', Validators.required], password: ['', [Validators.required, Validators.minLength(6)]] });
  }
  onSubmit() {
    if(this.registerForm.invalid)
      return;
    this.userService.register(this.registerForm.value).subscribe(resp => {
      //console.log("resp::"+JSON.stringify(resp.json()));
      this.user = resp.json();
     this.userService.sendSubmitMessage(this.user.firstName + "Registered Successfully");
    });
  }
}
