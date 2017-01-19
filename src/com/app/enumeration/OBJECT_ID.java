package com.app.enumeration;

import java.util.Calendar;

public enum OBJECT_ID {
	DEFAULT(Calendar.getInstance().get(Calendar.YEAR),"DID"),
	COURSE(Calendar.getInstance().get(Calendar.YEAR),"CID"),
	STUDENT(Calendar.getInstance().get(Calendar.YEAR),"SID"),
	YEAR(Calendar.getInstance().get(Calendar.YEAR),"YID");
	
	private final int year;
	private final String prefix;


	private OBJECT_ID(int year, String prefix){
		this.year = year;
		this.prefix = prefix;
	}

	public int getYear() {
		return year;
	}

	public String getPrefix() {
		return prefix;
	}

}
