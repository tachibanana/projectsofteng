package com.app.listener;

import com.app.event.LoginEvent;

public interface LoginListener {

	public void controllerExited(LoginEvent event);
}
