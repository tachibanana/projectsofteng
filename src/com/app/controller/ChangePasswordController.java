package com.app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.event.FocusEvent;
import com.app.listener.ControllerListener;
import com.app.model.User;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangePasswordController implements Initializable, ControllerListener{
	
	@FXML
	private PasswordField oldPasswordField;
	@FXML
	private PasswordField newPasswordField;
	@FXML
	private PasswordField confirmPasswordField;
	@FXML
	private Button buttonUpdate;
	@FXML
	private Button buttonCancel;
	@FXML
	private VBox messageBox;
	@FXML
	private Label messageLabel;
	private static User user;
	private static DBManager manager;
	private static ChangePasswordController changePasswordController;
	
	@FXML
	public void updateOnAction(ActionEvent event){
		if(user.getPassword().equals(oldPasswordField.getText().trim())){
			if(newPasswordField.getText().trim().equals(confirmPasswordField.getText().trim())){
				if(!oldPasswordField.getText().trim().equals(newPasswordField.getText().trim())){
					messageBox.setStyle("-fx-background-color:#2ecc71");
					messageLabel.setText("You have successfully change your password");
				}else{
					messageBox.setStyle("-fx-background-color:#e74c3c;");
					messageLabel.setText("You cannot reuse your old password.");
				}
			}
		}else{
			messageBox.setStyle("-fx-background-color:#e74c3c");
			messageLabel.setText("Incorrect password!");
			oldPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
		}	
	}
	
	
	@FXML
	public void cancelOnAction(ActionEvent event){
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void handleOnKeyEvent(KeyEvent event){
		
		if((oldPasswordField.getText().trim().equals("") || newPasswordField.getText().trim().equals("")
				|| confirmPasswordField.getText().trim().equals(""))) 
			buttonUpdate.setDisable(true);
		else if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim())){
			buttonUpdate.setDisable(true);
			confirmPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
		}else
			buttonUpdate.setDisable(false);
		
		//new password
		if(event.getSource().equals(newPasswordField)){
			if(newPasswordField.getText().matches("[a-zA-Z0-9]{6,26}+")){
				newPasswordField.setStyle("-fx-border-color: #2ecc71;-fx-border-width: 2;");
				
				messageBox.setStyle("-fx-background-color:#2ecc71;");
				messageLabel.setText("New passwords are required to be a minimum of 6 characters in length.");
			}else{
				newPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				
				messageBox.setStyle("-fx-background-color:#e67e22;");
				messageLabel.setText("New passwords are required to be a minimum of 6 characters in length.");
			}
		
		//confirm password
		}else if(event.getSource().equals(confirmPasswordField)){
			
			if(confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim())){
				confirmPasswordField.setStyle("-fx-border-color: #2ecc71;-fx-border-width: 2;");
				
				messageBox.setStyle("-fx-background-color:#2ecc71;");
				messageLabel.setText("The confirm password must match the new password entry.");
				
			}else{
				confirmPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				
				messageBox.setStyle("-fx-background-color:#e67e22;");
				messageLabel.setText("The confirm password must match the new password entry.");

			}
		}
		
	}
	
	public void focusGained(FocusEvent event){
		//if new password get focused
		if(event.getClazz().equals("newPasswordField")){
			newPasswordField.setStyle("-fx-border-width:0;");
			
			if(!newPasswordField.getText().matches("[a-zA-Z0-9]{6,26}+")){
				messageBox.setStyle("-fx-background-color:#e67e22;");
				messageLabel.setText("New passwords are required to be a minimum of 6 characters in length.");
			}
		//if confirm password get focused
		}else if(event.getClazz().equals("confirmPasswordField")){
			confirmPasswordField.setStyle("-fx-border-width:0;");
			
			//If confirm password not match the new password entry
			if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim())){
				messageBox.setStyle("-fx-background-color:#e67e22;");
				messageLabel.setText("The confirm password must match the new password entry.");
			}
		//if old password get focused	
		}else
			oldPasswordField.setStyle("-fx-border-width:0;");
			
	}
	
	public void focusLost(FocusEvent event){
		if(event.getClazz().equals("newPasswordField")){
			if(!newPasswordField.getText().matches("[a-zA-Z0-9]{6,26}+"))
				newPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
			else
				newPasswordField.setStyle("-fx-border-color: #2ecc71;-fx-border-width: 2;");
			
		}else{
			if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim())
					|| confirmPasswordField.getText().trim().equals(""))
				confirmPasswordField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
			else
				confirmPasswordField.setStyle("-fx-border-color: #2ecc71;-fx-border-width: 2;");
			
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		buttonUpdate.setDisable(true);
		
		newPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldProperty, Boolean newProperty){
				FocusEvent event = new FocusEvent();
				event.setClazz("newPasswordField");
				if(newProperty)
					focusGained(event);
				else
					focusLost(event);	
			}
		});
		
		confirmPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldProperty, Boolean newProperty){
				FocusEvent event = new FocusEvent();
				event.setClazz("confirmPasswordField");
				if(newProperty)
					focusGained(event);
				else
					focusLost(event);	
			}
		});
		
		oldPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldProperty, Boolean newProperty) {
				FocusEvent event = new FocusEvent();
				event.setClazz("oldPasswordField");
				if(newProperty)
					focusGained(event);
				
			}
		});
		
		
	}

	@Override
	public void controllerLoad(ControllerEvent event) {
		user = (User) event.getAttribute();
		manager = event.getManager();
	}

	public static ChangePasswordController getInstance(){
		if(changePasswordController == null)
			changePasswordController = new ChangePasswordController();
		return changePasswordController;
	}
}
