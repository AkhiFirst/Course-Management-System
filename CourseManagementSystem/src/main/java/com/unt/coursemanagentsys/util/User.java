package com.unt.coursemanagentsys.util;

import java.util.Calendar;

public class User {
private String id;
private String firstName;
private String lastName;
private String email;
private String deptName;
private String username;
private String password;
private Boolean userExists=false;
private Boolean validUser=false;
private int role_id;
private String semester;
private int year;


public String getSemester() {
	if((Calendar.getInstance().get(Calendar.MONTH))>=1 && (Calendar.getInstance().get(Calendar.MONTH))<=4)
		semester="Spring";
	else if((Calendar.getInstance().get(Calendar.MONTH))>=5 && (Calendar.getInstance().get(Calendar.MONTH))<=7)
		semester="Summer";
	else
		semester="Fall";
return semester;
}
public void setSemester(String semester) {
	this.semester = semester;
}
public int getYear() {
	year = Calendar.getInstance().get(Calendar.YEAR);
	return year;
}
public void setYear(int year) {
	this.year = year;
}

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDeptName() {
	return deptName;
}
public void setDeptName(String deptName) {
	this.deptName = deptName;
}


public Boolean getValidUser() {
	return validUser;
}
public void setValidUser(Boolean validUser) {
	this.validUser = validUser;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Boolean getUserExists() {
	return userExists;
}
public void setUserExists(Boolean userExists) {
	this.userExists = userExists;
}
public int getRole_id() {
	return role_id;
}
public void setRole_id(int role_id) {
	this.role_id = role_id;
}


}
