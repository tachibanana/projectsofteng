package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Properties;

import com.app.model.Attempt;

public class Logs {
	
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
			int interval = Parser.getIntervalOnMinute(calendar, Calendar.getInstance());
			return (interval <= 60 ? false : true );
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
		
	}

}
