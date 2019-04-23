package com.unt.coursemanagentsys.service;

import java.util.List;

import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;


public interface CourseManagementSysService {
public User userValidate(String username, String password);
public int register(User user);
public List<Department> fetchAllDepartments();
public int resetPassword(User user);
public List<Course> getCourses(User user);
public List<String> getCourseRelatedFiles(Course course);
public List<Assignment> getAssignementFilesForInstructor(Course course);
public List<Assignment> getAssignementFilesForStudent(Course course);
}
