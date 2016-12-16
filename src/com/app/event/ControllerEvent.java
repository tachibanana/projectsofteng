package com.app.event;

import com.app.database.DBManager;

public class ControllerEvent {
	
	private DBManager manager;
	private  String clazz;

	public DBManager getManager() {
		return manager;
	}

	public void setManager(DBManager manager) {
		this.manager = manager;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
}
