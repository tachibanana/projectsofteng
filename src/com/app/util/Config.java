package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.app.database.DBUserLibrary;

public class Config {
	
	public static DBUserLibrary getConnectionConfig(){
		try{
			FileInputStream fileReader = 
					new FileInputStream(ResourceLoader.dir() + "/imp/other/config/config.properties");
			DBUserLibrary dbLibrary = null;
			Properties prop = new Properties();
			prop.load(fileReader);
			
			String[] properties = new String[7];
			properties[0] = prop.getProperty("db.url");
			properties[1] = prop.getProperty("db.host");
			properties[2] = prop.getProperty("db.port");
			properties[3] = prop.getProperty("db.catalog");
			properties[4] = prop.getProperty("db.username");
			properties[5] = prop.getProperty("db.password");
			properties[6] = prop.getProperty("db.classname");
			
			Boolean flag = true;
			for(String eachString : properties){
				if(eachString == null)
					flag = false;
			}
			
			if(flag)
				dbLibrary = new DBUserLibrary(
						properties[0] + properties[1] + ":" + properties[2] + "/",
						properties[3],
						properties[4],
						properties[5],
						properties[6]);
			
			return dbLibrary;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setConnectionConfig(String host, String port,
			String catalog, String username, String password){
			
		try{
			Properties prop = new Properties();
			prop.setProperty("db.url", "jdbc:mysql://");
			prop.setProperty("db.host", host);
			prop.setProperty("db.port", port);
			prop.setProperty("db.catalog", catalog);
			prop.setProperty("db.username", username);
			prop.setProperty("db.password", password);
			prop.setProperty("db.classname", "com.mysql.jdbc.Driver");
			
			prop.store(new FileOutputStream(ResourceLoader.dir() + "/imp/other/config/config.properties"), null);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
