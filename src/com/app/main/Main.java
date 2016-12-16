package com.app.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

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
			initilizedDatabaseConn();
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
	public void initilizedDatabaseConn(){
		Scanner config = getConfig();
		
		manager = new DBLoginManager();
		manager.setDatabaseConnection(
				new MySQLConnection(
						config.nextLine().trim(),
						config.nextLine().trim(),
						config.nextLine().trim(),
						config.nextLine().trim(),
						config.nextLine().trim()));
		manager.openConnection();
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
