package com.unt.coursemanagentsys.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.unt.coursemanagentsys.service.CourseManagementSysServiceImpl;
import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.RegisterCourseRequestBody;
import com.unt.coursemanagentsys.util.StudentVO;
import com.unt.coursemanagentsys.util.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courseManagementSysController")
public class CourseManagementSysController {

	@Autowired
	CourseManagementSysServiceImpl courseManagementSysServiceImpl;

	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public User userValidate(@RequestBody User user) {
		return courseManagementSysServiceImpl.userValidate(user.getUsername(), user.getPassword());
	}

	@PostMapping(path = "/register", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public int register(@RequestBody User user) {
		return courseManagementSysServiceImpl.register(user);
	}
	
	@GetMapping(path="/fetchDeptNames", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Department> fetchAllDepartments(){
		return courseManagementSysServiceImpl.fetchAllDepartments();
	}
	
	@PostMapping(path="/resetPassword", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public int resetPassword(@RequestBody User user){
		return courseManagementSysServiceImpl.resetPassword(user);
	}
	
	@PostMapping(path = "/addassignemnt/{title}/{instructorId}")
	public String uploadAssignment(@RequestParam("uploadFile") List<MultipartFile> multipartFiles,@PathVariable("title") String title,@PathVariable("instructorId") String instructorId) {
		return courseManagementSysServiceImpl.uploadAssignment(multipartFiles, title, instructorId);
	}
	
	@PostMapping(path = "/uploadcourse/{title}")
	public Boolean uploadCourse(@RequestParam("uploadFile") List<MultipartFile> multipartFiles,@PathVariable("title") String title) {
		return courseManagementSysServiceImpl.uploadCourse(multipartFiles, title);
	}
	
	@PostMapping(path = "/getAddCourse", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Course> getAddCoursesList(@RequestBody User user){
		return courseManagementSysServiceImpl.getAddCoursesList(user);
	}
	@PostMapping(path = "/registerCourse", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public int registerCourse(@RequestBody RegisterCourseRequestBody requestBody) {
		return courseManagementSysServiceImpl.registerCourse(requestBody.getUser(), requestBody.getCourse());
	}
	
	
	@PostMapping(path = "/getcourses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Course> getCourses(@RequestBody User user) {
		return courseManagementSysServiceImpl.getCourses(user);
		
	}
	
	@PostMapping(path = "/getcourserelatedfiles")
	public List<String> getCourseRelatedFiles(@RequestBody Course course) {
		return courseManagementSysServiceImpl.getCourseRelatedFiles(course);
	}
    
    @PostMapping(path = "/getassignementfilesforinstructor")
    public List<Assignment> getAssignementFilesForInstructor(@RequestBody Course course) {
    	return courseManagementSysServiceImpl.getAssignementFilesForInstructor(course);
    }
    
    @PostMapping(path = "/getAssignementfilesforstudent")
    public List<Assignment> getAssignementFilesForStudent(@RequestBody Course course) {
    	return courseManagementSysServiceImpl.getAssignementFilesForStudent(course);
    }
    
    @PostMapping(path = "/downloadcoursefile")
    public byte[] downloadCourseFile(@RequestBody Course course) {
    	return courseManagementSysServiceImpl.downloadCourseFile(course);
    }
    
    @PostMapping(path = "/downloadassignmetfile")
    public byte[] downloadAssignmetFile(@RequestBody Assignment assignemt) {
    	return courseManagementSysServiceImpl.downloadAssignmetFile(assignemt);
    }
    
    @PostMapping(path = "/uploadassignmentbyinstructor/{title}")
    public String uploadAssignmentByInstructor(@RequestParam("uploadFile") List<MultipartFile> multipartFiles,@PathVariable("title") String title) {
    	return courseManagementSysServiceImpl.uploadAssignmentByInstructor(multipartFiles, title );
    }
}
