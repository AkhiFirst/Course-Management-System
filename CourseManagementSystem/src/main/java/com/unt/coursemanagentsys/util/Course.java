package com.unt.coursemanagentsys.util;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Course {
	private String title;
	private Boolean isTA;
	private String semester;
	private String instructorId;
	private String type;
	private String courseFileName;
	List<MultipartFile> multipartFiles;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the isTA
	 */
	public Boolean getIsTA() {
		return isTA;
	}
	/**
	 * @param isTA the isTA to set
	 */
	public void setIsTA(Boolean isTA) {
		this.isTA = isTA;
	}
	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	/**
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the courseFileName
	 */
	public String getCourseFileName() {
		return courseFileName;
	}
	/**
	 * @param courseFileName the courseFileName to set
	 */
	public void setCourseFileName(String courseFileName) {
		this.courseFileName = courseFileName;
	}
	/**
	 * @return the multipartFiles
	 */
	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}
	/**
	 * @param multipartFiles the multipartFiles to set
	 */
	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	
	

}
