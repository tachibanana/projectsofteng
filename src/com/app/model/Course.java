package com.app.model;

import java.util.List;

public class Course {
	
	private String courseId;
	private String courseCode;
	private String courseDefinition;
	private List<Year> listOfYear;
	
	
	
	public Course(String courseId, String courseCode, String courseDefinition,
			List<Year> listOfYear) {
		super();
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseDefinition = courseDefinition;
		this.listOfYear = listOfYear;
	}

	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseDefinition() {
		return courseDefinition;
	}
	
	public void setCourseDefinition(String courseDefinition) {
		this.courseDefinition = courseDefinition;
	}
	
	public List<Year> getListOfYear() {
		return listOfYear;
	}
	
	public void setListOfYear(List<Year> listOfYear) {
		this.listOfYear = listOfYear;
	}
	
	

	
	
	
}
