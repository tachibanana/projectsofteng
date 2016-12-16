package com.app.util;

import com.app.event.ControllerEvent;
import com.app.event.LoginEvent;
import com.app.listener.ControllerListener;
import com.app.listener.LoginListener;

public class Initializer {
	
	private static LoginListener loginListener;
	private static ControllerListener controllerListener;
	private static Initializer initializer;
	
	public static Initializer getInstance(){
		if(initializer == null)
			initializer = new Initializer();
		return initializer;
	}
	
	public static void callLoginListener(LoginEvent event){
		loginListener.controllerExited(event);
	}
	
	public static void callControllerListener(ControllerEvent event){
		controllerListener.controllerLoad(event);
	}

	public static void addLoginListener(LoginListener listener){
		loginListener = listener;
	}
	
	public static void addControllerListener(ControllerListener listener){
		controllerListener = listener;
	}
}
