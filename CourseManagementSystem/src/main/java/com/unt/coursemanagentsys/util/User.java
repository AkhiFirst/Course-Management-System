package com.unt.coursemanagentsys.util;

public class User {
private String id;
private String firstname;
private String lastname;
private String email;
private String deptName;
private String username;
private String password;
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
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
private Boolean userExists=false;
private Boolean validUser=false;

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

}
