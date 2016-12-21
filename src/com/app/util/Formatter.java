package com.app.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formatter {

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
	
	public static int getIntervalOnMinute(Calendar calendar1 , Calendar calendar2 ){
		try{
			int timeOnMinute = 0;
			int hour = 0;
			int minute = 0;
			
			if(calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
					calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
							calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)){
				
				if((hour = calendar2.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY)) >= 0){
					timeOnMinute = hour * 60;
					minute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE);
					timeOnMinute += minute + 1;
								
				}else if((minute = calendar2.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE)) >= 0){
					timeOnMinute += minute + 1;
				}
			}		
			return timeOnMinute * 1 ;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
