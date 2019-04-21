package com.unt.coursemanagentsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.coursemanagentsys.dao.CourseManagementSysDaoImpl;
import com.unt.coursemanagentsys.util.User;

@Service
public class CourseManagementSysServiceImpl implements CourseManagementSysService {
	@Autowired
	CourseManagementSysDaoImpl dao;

	public User userValidate(String username, String password) {
		User user = new User();
		if (dao.userExists(username)== 0)
		{
			user.setUsername(username);
			return user;
		} else {
			user = dao.userValidate(username);
			user.setUserExists(true);
			if (password.equals(user.getPassword()))
				user.setValidUser(true);
			return user;
		}
	}

	@Override
	public Boolean register(User user) {
		// TODO Auto-generated method stub
		if(dao.userIdProvided(user.getId())!=0 &&dao.register(user)==true)
			return true;
		else
			return false;
	}
}
