package com.app.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.dao.IDGeneratorDAO;
import com.app.model.hibernate.Id;

public class GeneratorDAOImpl implements IDGeneratorDAO{

	private final SessionFactory sessionFactory;
	
	public GeneratorDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Id getNext() {
		try{
			Id id = (Id) sessionFactory.openSession().get(Id.class, 1);
			if(id == null)
				throw new Exception();
			return id;
		}catch(Exception e){
			
			System.out.print("tbl_object_id not exist!\n");
			System.out.println("tbl_object_id is creating..");
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(new Id(1 , "0001" , "0001"));
			session.getTransaction().commit();
		
			return (Id) sessionFactory.openSession().get(Id.class, 1);
		}
	}
}
