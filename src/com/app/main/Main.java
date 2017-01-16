package com.app.main;

import com.app.database.DBManager;
import com.app.database.DatabaseConnection;
import com.app.database.MySQLConnection;
import com.app.database.SQLiteConnection;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.model.user.User;
import com.app.util.Config;
import com.app.window.ConfigWindow;
import com.app.window.LoginWindow;
import com.app.window.PrimaryWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements LoginListener{
	
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
			DatabaseConnection config = null;
			boolean isSuccess = false;
			manager = new DBManager();

			if(Config.getConnectionConfig() != null){
				if(Config.getConnectionConfig().getClassName().equals("com.mysql.jdbc.Driver"))
					 config = new MySQLConnection(Config.getConnectionConfig());
				else if(Config.getConnectionConfig().getClassName().equals("org.sqlite.JDBC"))
					 config = new SQLiteConnection(Config.getConnectionConfig());
				
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
