package com.app.event;

import com.app.model.user.User;

public class LoginEvent {
	
	private Boolean isSuccess = false;
	private User user;
	
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
