package com.app.model.user;

public abstract class User {
	
	private String username;
	private String password;
	private String accessType;
	private boolean isActivate;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccessType() {
		return accessType;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	
	public boolean isActivate() {
		return isActivate;
	}
	
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	

}
