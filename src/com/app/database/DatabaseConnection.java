package com.app.database;

import java.sql.Connection;

public interface DatabaseConnection {
	
	public Connection getConnection();
	public String getConnectionURL();
	public String getConnectionStatus();

}
