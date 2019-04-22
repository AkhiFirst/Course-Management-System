import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { Department } from '../../model/department';
import { Router } from '@angular/router';
import { PasswordValidation } from '../../password.validation';
import { DropdownValidation } from '../../dropdown.required.validation';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  user: User;
  userRegistered: number;
  department: Department;
  submitted = false;
  loading = false;
  departments: Array<Department>;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({ id: ['', Validators.required], firstName: ['', Validators.required], lastName: ['', Validators.required], email: ['', Validators.required], deptName: '', username: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(6)]], password: ['', [Validators.required, Validators.minLength(6)]], confirmPassword: ['', [Validators.required]] }, { validator:[PasswordValidation.MatchPassword,DropdownValidation.DeptRequired]});
    this.fetchDepartments();
  }
  get f() { return this.registerForm.controls;}
  onSubmit() {
    this.submitted=true;
    if (this.registerForm.invalid)
      return;
    this.userService.register(this.registerForm.value).subscribe(resp => {
      let flag = resp.json(); 
      this.userRegistered = flag;

      if (this.userRegistered == 1) {
        this.userService.sendSubmitMessage("User Registered Successfully");
        alert("User Registered Successfully. Please login");
        this.router.navigate(['login']);
      }
      else if(this.userRegistered == 0){
        this.userService.sendSubmitMessage("Error occured");
        alert("Unexpected error");
      }
      else if(this.userRegistered == 2){
        this.userService.sendSubmitMessage("Duplicate username");
        alert("Username already taken. Please enter different username");
      }
      else if(this.userRegistered == 3){
        this.userService.sendSubmitMessage("Already registered");
        alert("You already have an account");
      }
      else {
        this.userService.sendSubmitMessage("Invalid user");
        alert("Please enter your user ID provided by the university or contact university help");
      }
    });
  }
  fetchDepartments() {
    this.userService.fetchAllDepartments().subscribe(resp => {
      this.departments = <Department[]>resp.json();
    });
  }
}
