package com.app.model.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public abstract class User {
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="access_type")
	private String accessType;
	
	@Column(name="is_activated")
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
