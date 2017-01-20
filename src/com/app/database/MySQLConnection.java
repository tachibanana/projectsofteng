package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection extends DBUserLibrary implements DatabaseConnection{

	private boolean isConnected = false;
	
	public MySQLConnection(DBUserLibrary lib) {
			super(lib.getUrl(), lib.getCatalog(), lib.getUser(), lib.getPassword(), lib.getClassName());
	}
	
	@Override
	public Connection getConnection() {
		
		try {
			Class.forName(getClassName());
			Connection conn = DriverManager.getConnection(getConnectionURL());
			isConnected = true;
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			//e.printStackTrace();
			isConnected = false;
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		// TODO Auto-generated method stub
		return getUrl() + getCatalog() + "?user=" + getUser() + "&password=" + getPassword();
	}

	@Override
	public Boolean isConnected() {
		return isConnected;
	}

}
