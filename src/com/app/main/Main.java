package com.app.main;

import java.util.Scanner;

import com.app.database.DBManager;
import com.app.database.MySQLConnection;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.model.User;
import com.app.util.Instruction;
import com.app.window.ConfigWindow;
import com.app.window.LoginWindow;
import com.app.window.PrimaryWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements LoginListener{
	
	private static Main main;
	private DBManager manager;
	private static Boolean isLoginSuccess = false;
	private static volatile User user;

	@Override
	public void start(Stage primaryStage){
		try{
			
			while(!initilizeDatabaseConn()){
				ConfigWindow.display(manager);
			}
			
			while(!isLoginSuccess){
				LoginWindow.display(manager);
			}

			PrimaryWindow.display(primaryStage, manager, user);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void controllerExited(LoginEvent event) {
		isLoginSuccess = event.getIsSuccess();
		user = event.getUser();
	}
	
	//Initialize database connection
	public Boolean initilizeDatabaseConn(){
		try{
			Scanner config = Instruction.getConfig();
			Boolean flag = false;
			manager = new DBManager();
			
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
