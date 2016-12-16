package com.app.controller;

import java.io.FileOutputStream;

import com.app.database.DBManager;
import com.app.database.MySQLConnection;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConfigController implements ControllerListener{
	
	@FXML
	private Button buttonTest;
	@FXML
	private Button buttonSave;
	@FXML
	private TextField hostField;
	@FXML
	private TextField portField;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField catalogField;
	@FXML
	private VBox messagePanel;
	@FXML
	private Label messageLabel;

	
	private static DBManager manager;
	private static ConfigController configController;
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		
		if(event.getClazz().trim().equals(getClass().getCanonicalName())){
			manager = event.getManager();	
		}
	}
	
	@FXML
	public void handleTestOnAction(){
		String url = "jdbc:mysql://"+ hostField.getText() +":"+ portField.getText() +"/";
		String user = userField.getText();
		String password = passwordField.getText();
		String catalog = catalogField.getText();
		String className = "com.mysql.jdbc.Driver";
		
		manager.setDatabaseConnection(new MySQLConnection(url,catalog,user,password,className));
		manager.openConnection();
		
		if(manager.getConnection() == null){
			
			messagePanel.setStyle("-fx-background-color:#e74c3c");
			messageLabel.setText("ERROR : Bad connection. Please try again");
			addFadeInAnimation(messagePanel);
			
		}else{
			messagePanel.setStyle("-fx-background-color:#2ecc71");
			messageLabel.setText("SUCCESS : Database connected.");
			addFadeInAnimation(messagePanel);
	
			hostField.setDisable(true);
			portField.setDisable(true);
			catalogField.setDisable(true);
			userField.setDisable(true);
			passwordField.setDisable(true);
			
			buttonSave.setDisable(false);
			buttonTest.setDisable(true);
		}
	}
	
	private void addFadeInAnimation(Node node){
		try{
			FadeTransition ft = new FadeTransition(Duration.millis(1500), node);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.play();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleSaveOnAction(ActionEvent event) throws Exception{
		StringBuilder str = new StringBuilder();
		str.append("jdbc:mysql://" + hostField.getText() + ":" + portField.getText() + "/\n");
		str.append(catalogField.getText() + "\n");
		str.append(userField.getText() + "\n");
		str.append(passwordField.getText() + "\n");
		str.append("com.mysql.jdbc.Driver\n");
		
		FileOutputStream writer = new FileOutputStream("imp/other/config/config.txt");
		writer.write(str.toString().getBytes());
		
		if(writer != null){
			writer.close();
		}
		
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
		
	}
	
	
	public static ConfigController getInstance(){
		if(configController == null)
			configController = new ConfigController();
		return configController;
	}

}
