package com.app.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.app.database.DBLoginManager;
import com.app.database.DBManager;
import com.app.database.MySQLConnection;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.util.Instruction;
import com.app.window.ConfigWindow;
import com.app.window.LoginWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements LoginListener{
	
	//private Stage primaryStage;
	private static Main main;
	private DBManager manager;
	private Boolean isLoginSuccess = false;
	private static final List<Calendar> attemp = new ArrayList<Calendar>();
	
	public final String DIRECTORY_PATH = getClass()
			.getResource("../../../")
			.toString()
			.replaceAll("/bin", "");

	@Override
	public void start(Stage primaryStage){
		try{
			

			while(!initilizeDatabaseConn()){
				ConfigWindow.display(manager);
			}
			
			while(!isLoginSuccess){
				LoginWindow.display(manager);
			}
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void controllerExited(LoginEvent event) {
		
		attemp.add(Calendar.getInstance());
		
	}
	
	//Initialize database connection
	public Boolean initilizeDatabaseConn(){
		try{
			Scanner config = Instruction.getConfig();
			Boolean flag = false;
			manager = new DBLoginManager();
			
			if(config != null){
				
				manager.setDatabaseConnection(
						new MySQLConnection(
								config.nextLine().trim(),
								config.nextLine().trim(),
								config.nextLine().trim(),
								config.nextLine().trim(),
								config.nextLine().trim()));
				manager.openConnection();
				
				if(manager.getConnection() != null)
					flag = true;
			}
			
			return flag;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	//Return Main instance
	public static Main getInstance(){
		if(main == null)
			main = new Main();
		return main;
	}
	
	public static void main(String[] args){
		
		launch(args);
	}

}
