package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends DBUserLibrary implements DatabaseConnection{

	private static MySQLConnection connection;
	public MySQLConnection(String url, String catalog, String user, String password, String className) {
		super(url, catalog, user, password, className);
	}
	
	@Override
	public Connection getConnection() {
		
		try {
			Class.forName(getClassName());
			Connection conn = DriverManager.getConnection(getConnectionURL());
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
	public static MySQLConnection userLibrary(DBUserLibrary lib){
		if(lib != null)
			 connection = new MySQLConnection(
					lib.getUrl(), 
					lib.getCatalog(), 
					lib.getUser(), 
					lib.getPassword(), 
					lib.getClassName());
		return connection;
	}

	@Override
	public String getConnectionURL() {
		// TODO Auto-generated method stub
		return getUrl() + getCatalog() + "?user=" + getUser() + "&password=" + getPassword();
	}

	@Override
	public String getConnectionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
