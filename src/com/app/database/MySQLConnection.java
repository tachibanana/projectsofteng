package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends DBUserLibrary implements DatabaseConnection{

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
