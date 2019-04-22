package com.unt.coursemanagentsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.coursemanagentsys.dao.CourseManagementSysDaoImpl;
import com.unt.coursemanagentsys.util.Department;
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
	public int register(User user) {
		// TODO Auto-generated method stub
		if(dao.userIdProvided(user.getId())!=0) {// &&dao.register(user)==true)
			if(dao.userIdExists(user.getId())==0) {
				if(dao.userExists(user.getUsername())==0) {
					if(dao.register(user)==true)	
						return 1;
					else
						return 0;
				}
				else
					return 2;
			}
			else
				return 3;
		}
		else
			return 4;
	}

	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		return dao.fetchAllDepartments();
	}

	@Override
	public int resetPassword(User user) {
		// TODO Auto-generated method stub
		if(dao.userIdExists(user.getId())!=0) {
			if(user.getEmail().equals(dao.getEmail(user.getId())))
			{
				if(dao.resetPassword(user)==true)
					return 1;
				else
					return 0;
			}
			else
				return 2;
				
					
		}
		else
		return 3;
	}
}
