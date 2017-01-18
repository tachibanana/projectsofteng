package com.app.model;
@Deprecated
public class Student extends Person{
	
	private String email;
	private long studentNumber;
	private String course;
	private String year;
	private String listOfSubject;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public long getStudentNumber() {
		return studentNumber;
	}
	
	public void setStudentNumber(long studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getListOfSubject() {
		return listOfSubject;
	}
	
	public void setListOfSubject(String listOfSubject) {
		this.listOfSubject = listOfSubject;
	}

}
