package com.app.model;

public class Employee extends Person{
	
	
	private long employeeNumber;
	private String email;
	
	public Employee(long employeeNumber, String email) {
		super();
		this.employeeNumber = employeeNumber;
		this.email = email;
	}

	public long getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(long employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
