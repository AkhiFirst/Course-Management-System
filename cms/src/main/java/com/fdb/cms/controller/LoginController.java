package com.fdb.cms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fdb.cms.service.LoginServiceImpl;
import com.fdb.cms.utils.Student;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
	
@Autowired
LoginServiceImpl loginService;

@GetMapping(value ="/studentDetails",
produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
public ResponseEntity<List<Student>> getStudents(){
System.out.println("I entered and I will get required values");
List<Student> students=loginService.getStudentDetails();
return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
//return students;
}

}
