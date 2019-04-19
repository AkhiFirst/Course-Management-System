package com.unt.coursemanagentsys.dao;

import com.unt.coursemanagentsys.util.User;

public interface CourseManagementSysDao {
public User userValidate(String username);
public int userExists(String username);
public int userIdProvided(String id);
public void register(User user);
//public Course getCourses(String username);
}
