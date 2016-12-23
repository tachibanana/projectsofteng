package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Parser {

	public static String convertCalendarToString(Calendar calendar){
		try{
			StringBuilder calendarOnString = new StringBuilder();
			
			if(calendar != null){
				calendarOnString.append(calendar.get(Calendar.DAY_OF_MONTH) + "/");
				calendarOnString.append((calendar.get(Calendar.MONTH) + 1) + "/");
				calendarOnString.append(calendar.get(Calendar.YEAR) + " ");
				calendarOnString.append(calendar.get(Calendar.HOUR_OF_DAY) +":");
				calendarOnString.append(calendar.get(Calendar.MINUTE) + ":");
				calendarOnString.append(calendar.get(Calendar.SECOND));
			}
			return calendarOnString.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Calendar convertStringToCalendar(String stringCalendar){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(formatter.parse(stringCalendar));
			return calendar;
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
	}
}
