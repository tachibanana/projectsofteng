package com.app.model;

import java.util.List;

public class Course {
	
	private String courseCode;
	private String courseName;
	private String courseDefinition;
	private List<Year> listOfYear;

	public Course(String courseCode, String courseName, String courseDefinition, List<Year> listOfYear) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDefinition = courseDefinition;
		this.listOfYear = listOfYear;
	}



	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
