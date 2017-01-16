package com.app.model.user;

public class Year {

	public String yearId;
	private String yearCode;
	
	public Year(String yearId, String yearCode) {
		super();
		this.yearId = yearId;
		this.yearCode = yearCode;
	}
	
	public String getYearId() {
		return yearId;
	}
	
	public void setYearId(String yearId) {
		this.yearId = yearId;
	}
	
	public String getYearCode() {
		return yearCode;
	}
	
	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}
	
	

}
