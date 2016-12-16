package com.app.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.app.controller.ConfigController;
import com.app.controller.LoginController;
import com.app.database.DBLoginManager;
import com.app.database.DBManager;
import com.app.database.MySQLConnection;
import com.app.event.ControllerEvent;
import com.app.event.LoginEvent;
import com.app.listener.LoginListener;
import com.app.util.Initializer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application implements LoginListener{
	
	private Stage loginStage;
	private Stage primaryStage;
	private static Main main;
	private DBManager manager;
	private final String DIRECTORY_PATH = getClass()
			.getResource("../../../")
			.toString()
			.replaceAll("/bin", "");
	
	@Override
	public void start(Stage primaryStage){
		try{
			while(!initilizeDatabaseConn()){
				displayConfig();
			}
			
			displayLogin();
			 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void controllerExited(LoginEvent event) {
		
		System.out.println(event.getIsSuccess());
		
	}
	
	//Display login window
	public void displayLogin() throws IOException{
		loginStage = new Stage();
		loginStage.setTitle("Login");
		
		Parent root = (Parent) FXMLLoader.load(new URL(this.DIRECTORY_PATH + "/imp/fxml/login.fxml"));
		Scene scene = new Scene(root, 530 , 420);
		scene.getStylesheets().add(this.DIRECTORY_PATH + "/imp/css/login.css");
		
		ControllerEvent event = new ControllerEvent();
		event.setManager(manager);
		event.setClazz(LoginController.getInstance().getClass().getCanonicalName());
		
		Initializer.addControllerListener(LoginController.getInstance());
		Initializer.callControllerListener(event);
		
		loginStage.setScene(scene);
		loginStage.show();
	
	}
	
	//Display main window
	public void displayMain(){
		
	}

	//Initialize database connection
	public Boolean initilizeDatabaseConn(){
		try{
			Scanner config = getConfig();
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
			e.printStackTrace();
			return false;
		}
	}
	
	//Display Config
	public void displayConfig() throws IOException{
		Stage configStage = new Stage();
		configStage.setTitle("Database Configuration");
		configStage.setResizable(false);
		configStage.setMaximized(false);
		
		Parent root = FXMLLoader.load(new URL(this.DIRECTORY_PATH + "/imp/fxml/config.fxml"));
		
		Scene scene = new Scene(root, 350.0 , 320.0);
		scene.getStylesheets().add(this.DIRECTORY_PATH + "/imp/css/config.css");
		
		ControllerEvent event = new ControllerEvent();
		event.setManager(manager);
		event.setClazz(ConfigController.getInstance().getClass().getCanonicalName());
		
		Initializer.addControllerListener(ConfigController.getInstance());
		Initializer.callControllerListener(event);
		
		configStage.setScene(scene);
		configStage.showAndWait();
		
	}
	
	//Database config
	public Scanner getConfig(){
		try{
			Scanner scanner = new Scanner(new FileInputStream("imp/other/config/config.txt"));
			return (scanner.hasNext() ? scanner : null);
		}catch(Exception e){
			e.printStackTrace();
			return null;
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
