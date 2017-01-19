package com.app.main.hibernate;

import com.app.dao.impl.StudentDAOImpl;
import com.app.enumeration.OBJECT_ID;
import com.app.model.hibernate.Course;
import com.app.model.hibernate.Student;
import com.app.service.IDGeneratorService;

public class Main {
	
	public static void main(String args[]){
		Course course = new Course();
		course.setCourseId(IDGeneratorService.generate(OBJECT_ID.COURSE));
		course.setCourseCode("BSIT");
		course.setCourseName("Blahnalg");
		Student user = new Student(1 , "Manuel","Rafael","Estrada","rafaelmanuel00@gmail.com", course);
		StudentDAOImpl testDAO = new StudentDAOImpl();
		testDAO.addUser(user);
		
		//System.out.println(IDGeneratorUtil.generate(OBJECT_ID.STUDENT));
		
		//System.out.print(IDGeneratorService.generate());
		
		System.out.println("ateta");
	}
	
}
