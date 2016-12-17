package com.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Instruction {

	public static Scanner getConfig(){
		try{
			Scanner scanner = new Scanner(new FileInputStream("imp/other/config/config.txt"));
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
		
		FileOutputStream writer = new FileOutputStream("imp/other/config/config.txt");
		writer.write(str.toString().getBytes());
		writer.close();
	}
}
