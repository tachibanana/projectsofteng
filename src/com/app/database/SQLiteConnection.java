package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection extends DBUserLibrary implements DatabaseConnection{

	public boolean isConnected = false;
	public SQLiteConnection connection;
	private final String DB_CATALOG = "dbjavafx"; 
	
	public SQLiteConnection(DBUserLibrary lib) {
		super(lib.getUrl(), lib.getCatalog(), lib.getUser(), lib.getPassword(), lib.getClassName());
	}

	@Override
	public Connection getConnection() {
		try{
			Class.forName(getClassName());
			Connection conn = DriverManager.getConnection(getConnectionURL());
			isConnected = true;
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			isConnected = false;
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		return  getUrl() + ":" + DB_CATALOG;
	}

	@Override
	public Boolean isConnected() {
		return isConnected;
	}

}
