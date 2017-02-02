package com.app.main;

import com.app.database.DBManager;
import com.app.database.DatabaseConnection;
import com.app.database.MySQLConnection;
import com.app.database.SQLiteConnection;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.model.User;
import com.app.service.DBUserLibraryService;
import com.app.window.ConfigWindow;
import com.app.window.LoginWindow;
import com.app.window.PrimaryWindow;
import com.app.window.SubjectOptionWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainWithSpringFramework extends Application implements LoginListener{
	
	private static DBManager manager;
	private static boolean isLoginSuccess = false;
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
			
			//admin
			if(user.getAccessType().equals("ADMIN"))
				PrimaryWindow.display(primaryStage, manager, user);
			//student
			else if(user.getAccessType().equals("STUDENT"))
				SubjectOptionWindow.display(manager, user);
						
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
			
			DBUserLibraryService dbUserLibraryService = new DBUserLibraryService();
			DatabaseConnection config = null;
			
			boolean isSuccess = false;
			manager = new DBManager();

			if(dbUserLibraryService.getDbUserLibrary() != null){
				if(dbUserLibraryService.getDbUserLibrary().getClassName().equals("com.mysql.jdbc.Driver"))
					 config = new MySQLConnection(dbUserLibraryService.getDbUserLibrary());
				
				else if(dbUserLibraryService.getDbUserLibrary().getClassName().equals("org.sqlite.JDBC"))
					 config = new SQLiteConnection(dbUserLibraryService.getDbUserLibrary());
				
				if(config != null){
					manager.setDatabaseConnection(config);
					manager.openConnection();
					
					if(manager.getDatabaseConnection().isConnected())
						isSuccess = true;
				}
			}
			return isSuccess;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
