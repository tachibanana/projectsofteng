package com.app.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
	
	private DatabaseConnection dbconn;
	private  Connection conn;
	private static DBManager manager;
	
	public static DBManager getInstance(){
		if(manager == null)
			manager = new DBManager();
		return manager;
		
	}
	public void setDatabaseConnection(DatabaseConnection connection){
		dbconn = connection;
	}
	
	public void openConnection(){
		conn = dbconn.getConnection();
	}
	
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return conn;
	}

}
