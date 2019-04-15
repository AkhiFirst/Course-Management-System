package com.unt.coursemanagentsys.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unt.coursemanagentsys.util.StudentVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courseManagementSysController")
public class CourseManagementSysController {
	@PostMapping(path = "/getStudentDetails")
	public StudentVO getStudentDetails(@RequestBody StudentVO studentVO) {
		System.out.println("sooooooo");
		studentVO.setFirstName("jaaan");
		return studentVO;
	}
	
	@GetMapping("/getStr")
	public String getString() {
		return "hiii";
	}
}
