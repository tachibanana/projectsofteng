package com.app.model;

public class Subject {
	
	private String id;
	private String name;
	private String description;
	private int semester;
	private String prerequisite;
	private String courseId;
	private String category;
	private int unit;
	private int year;
	private boolean check;
	
	public Subject(){
		super();
	}
	public Subject(String id, String name, String description, int semester, String prerequisite, String courseId,
			String category, int unit, int year) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.semester = semester;
		this.prerequisite = prerequisite;
		this.courseId = courseId;
		this.category = category;
		this.unit = unit;
		this.year = year;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", description=" + description + ", semester=" + semester
				+ ", prerequisite=" + prerequisite + ", courseId=" + courseId + ", category=" + category + ", unit="
				+ unit + ", year=" + year + "]";
	}
	
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}


	
}
