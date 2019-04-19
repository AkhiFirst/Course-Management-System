package com.unt.coursemanagentsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unt.coursemanagentsys.service.CourseManagementSysServiceImpl;
import com.unt.coursemanagentsys.util.StudentVO;
import com.unt.coursemanagentsys.util.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courseManagementSysController")
public class CourseManagementSysController {

	@Autowired
	CourseManagementSysServiceImpl service;

	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User userValidate(@RequestBody User user) {
		return service.userValidate(user.getUsername().toUpperCase(), user.getPassword());
		// return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@PostMapping(path = "/register", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void register(@RequestBody User user) {
		
		service.register(user);
		// return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
