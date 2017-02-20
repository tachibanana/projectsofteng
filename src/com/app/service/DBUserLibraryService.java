//package com.app.service;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.app.database.DBUserLibrary;
//
//public class DBUserLibraryService {
//	
//	private DBUserLibrary dbUserLibrary;
//	ApplicationContext context = null;
//	
//	public DBUserLibraryService(){
//		
//		try{
//			 context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
//			 dbUserLibrary = context.getBean("dbUserLibrary" , DBUserLibrary.class);
//		}catch(Exception e){
//			dbUserLibrary = null;
//			e.printStackTrace();
//		}
//	}
//	
//	public DBUserLibrary getDbUserLibrary() {
//		return dbUserLibrary;
//	}
//
//	public void setDbUserLibrary(DBUserLibrary dbUserLibrary) {
//		this.dbUserLibrary = dbUserLibrary;
//	}
//	
//	public boolean saveDbUserLibrary(){
//		try{
//			
//			return true;
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//	}
//}
