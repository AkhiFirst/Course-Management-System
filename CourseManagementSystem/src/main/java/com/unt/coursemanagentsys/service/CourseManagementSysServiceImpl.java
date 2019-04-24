package com.unt.coursemanagentsys.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unt.coursemanagentsys.dao.CourseManagementSysDaoImpl;
import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;


@Service
public class CourseManagementSysServiceImpl implements CourseManagementSysService {
	@Autowired
	CourseManagementSysDaoImpl courseManagementSysDaoImpl;
	public static final String localPath = "F:\\Akhila project\\Files\\";

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
	public List<Course> getCourses(User user) {
		// TODO Auto-generated method stub
		return courseManagementSysDaoImpl.getCourses(user);
	}

	@Override
	public List<String> getCourseRelatedFiles(Course course) {
		List<String> fileNames = new ArrayList<>();
		try {
			File folder = new File(localPath+course.getType()+"\\"+course.getTitle());
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

	@Override
	public List<Assignment> getAssignementFilesForInstructor(Course course) {
		List<Assignment> assignmnetFilesList = new ArrayList<>();
		List<Assignment> assignmentsList = courseManagementSysDaoImpl.getAssignementFilesForInstructor(course);
		for (Assignment assignment : assignmentsList) {
			File folder = new File(localPath+course.getType()+"\\"+assignment.getStudentId()+"\\"+assignment.getCourseName());
			System.out.println("folderpath::"+folder.getPath());
			File[] files = folder.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					Assignment assign = new Assignment();
					assign.setCourseName(assignment.getCourseName());
					assign.setFileName(file.getName());
					assign.setStudentId(assignment.getStudentId());
					assignmnetFilesList.add(assign);
				}
			}
		}
		
		return assignmnetFilesList;
	}

	@Override
	public List<Assignment> getAssignementFilesForStudent(Course course) {
		List<Assignment> assignmentList = new ArrayList<>();
		try {
			File folder = new File(localPath+course.getType()+"\\"+course.getInstructorId()+"\\"+course.getTitle());
			System.out.println("folder::"+folder.getPath());
			File[] files = folder.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					Assignment assignment = new Assignment();
					assignment.setCourseName(course.getTitle());
					assignment.setFileName(file.getName());
					assignment.setStudentId(course.getInstructorId());
					assignmentList.add(assignment);
				}
			}
		} catch(Exception ex) {
			System.out.println("Exception in getAssignementFilesForStudent::"+ex.getMessage());
		}
		return assignmentList;
	}

	@Override
	public byte[] downloadCourseFile(Course course) {
		File targetFile = new File(localPath+course.getType()+"\\"+course.getTitle()+"\\"+course.getCourseFileName());
		byte[] pdfData = new byte[(int) targetFile.length()];
		try {
			FileInputStream targetInStream = new FileInputStream(targetFile);
			targetInStream.read(pdfData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfData;
	}

	@Override
	public byte[] downloadAssignmetFile(Assignment assignemt) {
		byte[] pdfData = null;
		try {
			File targetFile = new File(localPath+"Assignments"+assignemt.getStudentId()+"\\"+assignemt.getCourseName()+"\\"+assignemt.getFileName());
			FileInputStream targetInStream = new FileInputStream(targetFile);
			pdfData = sanitizeIS(targetInStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdfData;
	}

	public  static byte[] sanitizeIS(InputStream inIs) {
		ByteArrayOutputStream output = null;
		byte[] decodedInput = null;
		try {
			output = new ByteArrayOutputStream();
			char[] chArr = IOUtils.toCharArray(inIs,"UTF-8");
			for (char ch : chArr) {
				if ((ch == 0x9) || (ch == 0xA) || (ch == 0xD)
						|| ((ch >= 0x20) && (ch <= 0x10FFFF))) {
					output.write((int) ch);
				}
			}
			decodedInput = output.toByteArray();
		} catch (IOException ex) {
			System.out.println("Exception in sanitizeIS::"+ex.getMessage());
		} 
		return decodedInput;
	}

	@Override
	public String uploadAssignment(List<MultipartFile> multipartFiles, String title, String instructorId) {
		File directory = new File(localPath+"Assignments\\"+instructorId);
		if(!directory.exists()) {
			directory.mkdir();
			directory = new File(localPath+"Assignments\\"+instructorId+"\\"+title);
			if(!directory.exists()) {
				directory.mkdir();
			}
		}else {
			directory = new File(localPath+"Assignments\\"+instructorId+"\\"+title);
			if(!directory.exists()) {
				directory.mkdir();
			}
		}
		multipartFiles.forEach(multiparFile -> { File file = new File(localPath+"Assignments\\"+instructorId+"\\"+title+"\\"+ multiparFile.getOriginalFilename());
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

	@Override
	public String uploadCourse(List<MultipartFile> multipartFiles, String title) {
		File directory = new File(localPath+"Courses\\"+title);
		if(!directory.exists()) {
			directory.mkdir();
		}
		multipartFiles.forEach(multiparFile -> { File file = new File(localPath+"Courses\\"+title+"\\"+ multiparFile.getOriginalFilename());
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

}
