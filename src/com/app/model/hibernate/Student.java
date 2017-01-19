package com.app.model.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user_student")
public class Student extends User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="email_address")
	private String email;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="course_id")
	private Course course;
	
	public Student(){
		super();
	}
	
	
	public Student(int studentId, String lastName, String firstName, String middleName, String email, Course course) {
		this();
		this.studentId = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.email = email;
		this.course = course;
		
		setUsername(email);
		setPassword(String.valueOf(studentId));
		setAccessType("STUDENT");
		setActivate(true);
	}

	public int getStudentId() {
		return studentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

}