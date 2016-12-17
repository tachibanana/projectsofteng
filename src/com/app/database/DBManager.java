package com.app.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
	
	private DatabaseConnection dbconn;
	private  Connection conn;
	private static DBManager manager;
	
	public static final DBManager getInstance(){
		if(manager == null)
			manager = new DBManager();
		return manager;
		
	}
	public final void setDatabaseConnection(DatabaseConnection connection){
		dbconn = connection;
	}
	
	public final void openConnection(){
		conn = dbconn.getConnection();
	}
	
	public final void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final Connection getConnection(){
		return conn;
	}

}
