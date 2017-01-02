package com.app.controller;

import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;

public class StudentInfoController implements ControllerListener {

	@Override
	public void controllerLoad(ControllerEvent event) {
		System.out.println("tae");
		
	}

}
