package com.app.util;

import java.util.Calendar;

import com.app.database.DBManager;

public abstract class PasswordGenerator {
	
	public static String generate(String prefix , DBManager manager){
		
		StringBuilder id = new StringBuilder();
		id.append(prefix.toUpperCase());
		id.append(Calendar.getInstance().get(Calendar.YEAR));
		int numberOfUser = Integer.parseInt(manager.getTrackPassword(prefix).substring(7)) + 1;
		id.append(String.format("%04d", numberOfUser));
		
		return id.toString();
	}

}
