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
	
	@PostMapping(path = "/addAssignemnt")
	public String addAssignemnt(@RequestParam("uploadFile") List<MultipartFile> multipartFiles) {
		System.out.println("in addAssignemnt"+multipartFiles.size());
		multipartFiles.forEach(multiparFile -> { File file = new File("F:\\Akhila project\\Files\\" + multiparFile.getOriginalFilename());
		System.out.println("multiparFile.getOriginalFilename()::"+multiparFile.getOriginalFilename());
		try {
			multiparFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		});
		return "Success";
	}
	@PostMapping(path = "/getCourses")
	public List<Course> getCourses(@RequestBody User user) {
		return courseManagementSysServiceImpl.getCourses(user);
		
	}
	
	@PostMapping(path = "/getcourserelatedfiles")
	public List<String> getCourseRelatedFiles(@RequestBody Course course) {
		return courseManagementSysServiceImpl.getCourseRelatedFiles(course);
	}
	
	@PostMapping(path = "/getcoursefile")
    public void getCourseFile(HttpServletResponse response,@RequestBody Course course) throws Exception {

		 File file = new File("F:\\Akhila project\\Files"+"\\"+course.getType()+"\\"+course.getTitle()+"\\"+course.getCourseFileName());
		 System.out.println("file::"+file.getPath());
		  byte[] bytesArray = new byte[(int) file.length()]; 
		  
        streamReport(response, bytesArray, course.getCourseFileName());
    }
	@PostMapping(path = "/getassignmentfile")
	public void getAssignmentFile(HttpServletResponse response,@RequestBody Assignment assignment) throws Exception {

		 File file = new File("F:\\Akhila project\\Files\\Assignments\\"+assignment.getStudentId()+"\\"+assignment.getCourseName()+"\\"+assignment.getFileName());
		 System.out.println("file::"+file.getPath());
		  byte[] bytesArray = new byte[(int) file.length()]; 
		  
       streamReport(response, bytesArray, assignment.getFileName());
   }

    protected void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
    
    @PostMapping(path = "/getassignementfilesforinstructor")
    public List<Assignment> getAssignementFilesForInstructor(Course course) {
    	return courseManagementSysServiceImpl.getAssignementFilesForInstructor(course);
    }
    
    @PostMapping(path = "/getAssignementfilesforstudent")
    public List<Assignment> getAssignementFilesForStudent(Course course) {
    	return courseManagementSysServiceImpl.getAssignementFilesForStudent(course);
    }
}
