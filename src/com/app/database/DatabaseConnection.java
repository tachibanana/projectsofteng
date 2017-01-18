package com.app.database;

import java.sql.Connection;

@Deprecated
public interface DatabaseConnection {
	
	public Connection getConnection();
	public String getConnectionURL();
	public Boolean isConnected();

}
