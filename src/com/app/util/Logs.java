package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Properties;

import com.app.model.Attempt;

public abstract class Logs {
	
	public static Attempt getLoginAttempt(){
		try{
			Attempt attempt = new Attempt(0,null);
			Properties prop = new Properties();
			prop.load(new FileInputStream(ResourceLoader.dir() + "/imp/other/config/logs.properties"));
			
			int numberOfAttempt = (prop.getProperty("number.attempt") != null ?
					(prop.getProperty("number.attempt").matches("(\\d)+")? 
							(Integer.parseInt(prop.getProperty("number.attempt"))) : 0 ) :0);
			Calendar lastLogin = Parser
					.convertStringToCalendar(
							(prop.getProperty("last.login") != null ? prop.getProperty("last.login") : ""));
			
			if(numberOfAttempt != 0 || lastLogin != null){
				if(!isAttemptExpired(lastLogin))
					attempt = new Attempt(numberOfAttempt, lastLogin);
			}
			
			return attempt;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setLoginAttempt(Attempt attempt){
		try{
			Properties prop = new Properties();
			prop.setProperty("number.attempt", String.valueOf(attempt.getNumberOfAttempt()));
			prop.setProperty("last.login", Parser.convertCalendarToString(attempt.getLastAttempt()));
			prop.store(new FileOutputStream(ResourceLoader.dir() + "/imp/other/config/logs.properties"), null);			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static Boolean isAttemptExpired(Calendar calendar){
		try{
			int interval = getIntervalOnMinute(calendar, Calendar.getInstance());
			return (interval <= 60 ? false : true );
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
		
	}
	
	private static int getIntervalOnMinute(Calendar calendar1 , Calendar calendar2 ){
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
