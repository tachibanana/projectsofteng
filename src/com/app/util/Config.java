package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.app.database.DBUserLibrary;

public abstract class Config {
	
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
			
			if(flag && properties[6].trim().equals("com.mysql.jdbc.Driver"))
				dbLibrary = new DBUserLibrary(
						properties[0] + properties[1].trim() + ":" + properties[2].trim() + "/",
						properties[3].trim(),
						properties[4].trim(),
						properties[5].trim(),
						properties[6].trim());
			else if(flag && properties[6].trim().equals("org.sqlite.JDBC"))
				dbLibrary = new DBUserLibrary(properties[0].trim(), properties[3].trim(), null, null, properties[6].trim());
			
			return dbLibrary;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setConnectionConfig(String url, String host, String port,
			String catalog, String username, String password, String classname){
			
		try{
			Properties prop = new Properties();
			prop.setProperty("db.url", url == null ? "jdbc:mysql://" : url);
			prop.setProperty("db.host", host == null ? "" : host);
			prop.setProperty("db.port", port == null ? "" : port);
			prop.setProperty("db.catalog", catalog == null ? "" : catalog);
			prop.setProperty("db.username", username == null ? "" : username);
			prop.setProperty("db.password", password == null ? "" : password);
			prop.setProperty("db.classname", classname == null ? "com.mysql.jdbc.Driver" : classname);
			
			prop.store(new FileOutputStream(ResourceLoader.dir() + "/imp/other/config/config.properties"), null);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
