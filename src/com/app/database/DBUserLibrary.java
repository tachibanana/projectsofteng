package com.app.database;

public class DBUserLibrary {
	
	private String url;
	private String catalog;
	private String user;
	private String password;
	private String className;
	
	public DBUserLibrary(String url, String catalog, String user, String password, String className) {
		super();
		this.url = url;
		this.catalog = catalog;
		this.user = user;
		this.password = password;
		this.className = className;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	

}
