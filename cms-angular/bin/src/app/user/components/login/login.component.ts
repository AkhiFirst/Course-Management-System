import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { User } from '../../model/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  user: User;
  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({ username: ['', Validators.required], password: ['', [Validators.required, Validators.minLength(6)]] });
  }
  onSubmit() {
    if (this.loginForm.invalid)
      return;
    this.userService.login(this.loginForm.value).subscribe(resp => {
      console.log("resp::" + JSON.stringify(resp.json()));
      this.user = resp.json();
      if (this.user.userExists == false) {
        this.userService.sendSubmitMessage("Error::"+this.user.username + "not found");
        alert("You are not registered. Please go ahead and register");
      }
      else {
        if (this.user.validUser == false) {
          this.userService.sendSubmitMessage("Error::invalid password");
          alert("Incorrect password. Please re-enter the password");
        }
        else {
          this.userService.sendSubmitMessage("Success::"+this.user.username + "logged in Successfully");
          this.router.navigate(['/register']);
        }
      }
    });
  }
}