package com.app.controller;

import com.app.database.DBManager;
import com.app.database.DBUserLibrary;
import com.app.database.MySQLConnection;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.util.Config;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConfigController implements ControllerListener{
	
	@FXML
	private Button buttonConnect;
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
	@FXML
	private CheckBox checkEmbedded;
	private static DBManager manager;
	private static ConfigController configController;
	
	@FXML
	public void handleConnectOnAction(){
		try{
			String url = "jdbc:mysql://"+ hostField.getText() +":"+ portField.getText() +"/";
			String user = userField.getText();
			String password = passwordField.getText();
			String catalog = catalogField.getText();
			String className = "com.mysql.jdbc.Driver";
			manager.setDatabaseConnection(
					new MySQLConnection(new DBUserLibrary(url,catalog,user,password,className)));
	
			manager.openConnection();
			
			if(!manager.getDatabaseConnection().isConnected()){
				
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
				checkEmbedded.setDisable(true);
				
				buttonSave.setDisable(false);
				buttonConnect.setDisable(true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Deprecated
	@FXML
	public void handleSaveOnAction(ActionEvent event) throws Exception{
		if(checkEmbedded.isSelected())
			Config.setConnectionConfig("jdbc:sqlite",null, null,"dbjavafx", null, null,"org.sqlite.JDBC");
		else
			Config.setConnectionConfig(
					null,
					hostField.getText(), 
					portField.getText(), 
					catalogField.getText(), 
					userField.getText(), 
					passwordField.getText(),
					null);
		
		
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
		
	}
	
	@FXML
	public void handleCheckEmbeddedOnAction(ActionEvent event){
		try{
				hostField.setDisable(checkEmbedded.isSelected());
				portField.setDisable(checkEmbedded.isSelected());
				catalogField.setDisable(checkEmbedded.isSelected());
				userField.setDisable(checkEmbedded.isSelected());
				passwordField.setDisable(checkEmbedded.isSelected());
				buttonConnect.setDisable(checkEmbedded.isSelected());
				buttonSave.setDisable(!checkEmbedded.isSelected());
				if(checkEmbedded.isSelected())
					buttonSave.setText("Confirm");
				else
					buttonSave.setText("Save");		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		
		if(event.getClazz().trim().equals(getClass().getCanonicalName()))
			manager = event.getManager();
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
	
	public static ConfigController getInstance(){
		if(configController == null)
			configController = new ConfigController();
		return configController;
	}

}
