package com.app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.dao.UserDAO;
import com.app.model.hibernate.Student;
import com.app.util.hibernate.HibernateUtil;

public class StudentDAOImpl implements UserDAO<Student>{
	
	private SessionFactory sessionFactory;
	
	public StudentDAOImpl(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(Student user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(user.getCourse());
		session.getTransaction().commit();
	}

	@Override
	public void updateUser(Student user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> getListOfUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(Student user) {
		// TODO Auto-generated method stub
		
	}
}
