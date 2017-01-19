package com.app.model.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbl_object_id")
public class Id {
	
	@javax.persistence.Id
	@Column(name="object_id")
	private int objectId;
	
	@Column(name="student_id")
	private String nextStudentId;
	
	@Column(name="course_id")
	private String nextCourseId;
	
	public Id(){
		super();
	}

	public Id(int objectId, String nextStudentId, String nextCourseId) {
		super();
		this.objectId = objectId;
		this.nextStudentId = nextStudentId;
		this.nextCourseId = nextCourseId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getNextStudentId() {
		return nextStudentId;
	}

	public void setNextStudentId(String nextStudentId) {
		this.nextStudentId = nextStudentId;
	}

	public String getNextCourseId() {
		return nextCourseId;
	}

	public void setNextCourseId(String nextCourseId) {
		this.nextCourseId = nextCourseId;
	}

}
