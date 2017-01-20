package com.app.util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory(){
			if(sessionFactory == null)
				sessionFactory = new Configuration().configure().buildSessionFactory();
			return sessionFactory;
	}

	public static void closeSessionFactory(){
		sessionFactory.close();
		sessionFactory = null;
	}
}
