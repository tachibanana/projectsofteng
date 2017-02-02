package com.app.main;

import com.app.database.DBManager;
import com.app.database.DatabaseConnection;
import com.app.database.MySQLConnection;
import com.app.database.SQLiteConnection;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.model.User;
import com.app.util.Config;
import com.app.window.ConfigWindow;
import com.app.window.LoginWindow;
import com.app.window.PrimaryWindow;
import com.app.window.SubjectOptionWindow;

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
			
			//admin
			if(user.getAccessType().equals("ADMIN"))
				PrimaryWindow.display(primaryStage, manager, user);
			//student
			else if(user.getAccessType().equals("STUDENT"))
				SubjectOptionWindow.display(manager);
			else if(user.getAccessType().equals("EMPLOYEE"))
				//SubjectOptionWindow.display(manager);
				System.out.println("awesome");		
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void controllerExited(LoginEvent event) {
		isLoginSuccess = event.getIsSuccess();
		user = event.getUser();
	}
	
	@Deprecated
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
//		ApplicationContext context = new ClassPathXmlApplicationContext("jdbc_spring_config.xml");
//		DBUserLibrary user = context.getBean("dbUserLibrary" , DBUserLibrary.class);
//		DBManager manager = new DBManager();
//		manager.setDatabaseConnection(new MySQLConnection(user));
//		manager.openConnection();
//		System.out.println(manager.getAllSubjectByYearAndSem(1, 1));
//		System.out.println(user.toString());
	}

}
