package com.app.model;

public class Year {

	public int yearCode;
	private String student_year;
	
	public Year(int yearCode, String student_year) {
		this.yearCode = yearCode;
		this.student_year = student_year;
	}

	public int getYearCode() {
		return yearCode;
	}

	public void setYearCode(int yearCode) {
		this.yearCode = yearCode;
	}

	public String getStudent_year() {
		return student_year;
	}

	public void setStudent_year(String student_year) {
		this.student_year = student_year;
	}

}
