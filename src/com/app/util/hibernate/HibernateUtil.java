package com.app.util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibernateUtil {
	
	public static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory(){
			if(sessionFactory == null)
				return new Configuration().configure().buildSessionFactory();
			return null;
	}

	public static void closeSessionFactory(){
		sessionFactory.close();
		sessionFactory = null;	
	}
}
