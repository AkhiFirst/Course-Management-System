package com.unt.coursemanagentsys.util;

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
private String role;

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
/**
 * @return the role
 */
public String getRole() {
	return role;
}
/**
 * @param role the role to set
 */
public void setRole(String role) {
	this.role = role;
}

}
