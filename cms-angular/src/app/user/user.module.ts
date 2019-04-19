import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserService } from './services/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http, HttpModule } from '@angular/http';
import { UserRoutingModule } from './user-routing.module';
import { MatCardModule } from '@angular/material';

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    UserRoutingModule,
    MatCardModule
  ],
  providers: [
    UserService
  ],
  // exports: [LoginComponent,RegisterComponent]
})
export class UserModule { }
