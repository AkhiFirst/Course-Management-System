package com.unt.coursemanagentsys.dao;

import java.util.List;

import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;



public interface CourseManagementSysDao {
public User userValidate(String username);
public int userExists(String username);
public int userIdProvided(String id);
public Boolean register(User user);
public int userIdExists(String id);
public List<Department> fetchAllDepartments();
public Boolean resetPassword(User user);
public String getEmail(String id);
public List<Course> getCourses(User user);
//public Course getCourses(String username);
public List<Assignment> getAssignementFilesForInstructor(Course course);

public List<Course> getAddCoursesList(User user);
public int getRoleId(String id);
public Boolean registerInstructorCourse(User user, Course course);
public Boolean registerStudentCourse(User user, Course course);
public int countUserCourses(User user);

}
