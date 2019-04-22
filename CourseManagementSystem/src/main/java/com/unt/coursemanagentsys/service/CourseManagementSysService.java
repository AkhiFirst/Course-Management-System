package com.unt.coursemanagentsys.service;

import java.util.List;

import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;

public interface CourseManagementSysService {
public User userValidate(String username, String password);
public int register(User user);
public List<Department> fetchAllDepartments();
public int resetPassword(User user);
}
