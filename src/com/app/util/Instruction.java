package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import com.app.model.Attempt;

public class Instruction {

	public static Scanner getConfig(){
		try{
			Scanner scanner = new Scanner(
					new FileInputStream(ResourceLoader.dir() + "/imp/other/config/config.txt"));
			return (scanner.hasNext() ? scanner : null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void setConfig(String host,String port,String catalog,String user, String password) throws IOException{
		StringBuilder str = new StringBuilder();
		str.append("jdbc:mysql://" + host + ":" + port + "/\n");
		str.append(catalog + "\n");
		str.append(user + "\n");
		str.append(password + "\n");
		str.append("com.mysql.jdbc.Driver\n");
		
		FileOutputStream writer = new FileOutputStream(ResourceLoader.dir() + "/imp/other/config/config.txt");
		writer.write(str.toString().getBytes());
		writer.close();
	}
	
	public static void setLoginAttempt(Attempt attempt){
		try{
			
			FileOutputStream writer = new FileOutputStream(ResourceLoader.dir() + "/imp/other/config/logs.txt");
			
			if(attempt != null){
				if(attempt.getLastAttempt() != null){
					String string = attempt.getNumberOfAttempt() + "\n";
					string += Formatter.convertCalendarToString(attempt.getLastAttempt());
					writer.write(string.getBytes());
				}
			}else
				writer.write("".getBytes());
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static Boolean isAttepExpired(Calendar calendar){
		try{
			int interval = Formatter.getIntervalOnMinute(calendar, Calendar.getInstance());
			return (interval <= 60 ? false : true );
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
		
	}
	
	public static Attempt getLoginAttempt(){
		try{
			Attempt attempt = new Attempt(0 , null);
			Scanner scanner = new Scanner(
					new FileInputStream(ResourceLoader.dir() + "/imp/other/config/logs.txt"));
			if(scanner.hasNext()){
				//int numberOfAttempt = (scanner.nextLine().trim().matches("[\\d]+")? Integer.parseInt(scanner.nextLine().trim()) : 0);
				int numberOfAttempt = Integer.parseInt(scanner.nextLine().trim());
				Calendar lastAttempt = Formatter.convertStringToCalendar(scanner.nextLine().trim());
				if(lastAttempt != null){
					if(!isAttepExpired(lastAttempt))
						attempt = new Attempt(numberOfAttempt , lastAttempt);
					else
						setLoginAttempt(null);
					
				}
				scanner.close();
			}
			return attempt;
		}catch(Exception e){
			e.printStackTrace();
			return new Attempt(0 , Calendar.getInstance());
		}
	}
}
