package com.unt.coursemanagentsys.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;


public interface CourseManagementSysService {
public User userValidate(String username, String password);
public int register(User user);
public List<Department> fetchAllDepartments();
public int resetPassword(User user);
public List<Course> getCourses(User user);
public List<String> getCourseRelatedFiles(Course course);
public List<Assignment> getAssignementFilesForInstructor(Course course);
public List<Assignment> getAssignementFilesForStudent(Course course);
public byte[] downloadCourseFile(Course course);
public byte[] downloadAssignmetFile(Assignment assignemt);
public String uploadAssignment(List<MultipartFile> multipartFiles, String title, String instructorId);
public String uploadCourse(List<MultipartFile> multipartFiles, String title);
}
