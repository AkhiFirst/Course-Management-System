package com.unt.coursemanagentsys.service;

import com.unt.coursemanagentsys.util.User;

public interface CourseManagementSysService {
public User userValidate(String username, String password);
public void register(User user);
}