package com.unt.coursemanagentsys.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unt.coursemanagentsys.dao.CourseManagementSysDaoImpl;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;

@Service
public class CourseManagementSysServiceImpl implements CourseManagementSysService {
	@Autowired
	CourseManagementSysDaoImpl courseManagementSysDaoImpl;

	public User userValidate(String username, String password) {
		User user = new User();
		if (courseManagementSysDaoImpl.userExists(username)== 0)
		{
			user.setUsername(username);
			return user;
		} else {
			user = courseManagementSysDaoImpl.userValidate(username);
			user.setUserExists(true);
			if (password.equals(user.getPassword()))
				user.setValidUser(true);
			return user;
		}
	}

	@Override
	public int register(User user) {
		// TODO Auto-generated method stub
		if(courseManagementSysDaoImpl.userIdProvided(user.getId())!=0) {// &&dao.register(user)==true)
			if(courseManagementSysDaoImpl.userIdExists(user.getId())==0) {
				if(courseManagementSysDaoImpl.userExists(user.getUsername())==0) {
					if(courseManagementSysDaoImpl.register(user)==true)	
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
		return courseManagementSysDaoImpl.fetchAllDepartments();
	}

	@Override
	public int resetPassword(User user) {
		// TODO Auto-generated method stub
		if(courseManagementSysDaoImpl.userIdExists(user.getId())!=0) {
			if(user.getEmail().equals(courseManagementSysDaoImpl.getEmail(user.getId())))
			{
				if(courseManagementSysDaoImpl.resetPassword(user)==true)
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
	@Override
	public List<Course> getCurrentSemCourses(User user) {
		// TODO Auto-generated method stub
		return courseManagementSysDaoImpl.getCurrentSemCourses(user);
	}

	@Override
	public List<String> getAllFiles(Course course) {
		List<String> fileNames = new ArrayList<>();
		try {
			File folder = new File("F:\\Akhila project\\Files\\"+course.getType()+"\\"+course.getInstructorId()+"\\"+course.getTitle());
			System.out.println("folder::"+folder.getPath());
			File[] files = folder.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					fileNames.add(file.getName());
				}
			}
		} catch(Exception ex) {
			System.out.println("Exception in getAllFiles::"+ex.getMessage());
		}
		return fileNames;
	}
}
