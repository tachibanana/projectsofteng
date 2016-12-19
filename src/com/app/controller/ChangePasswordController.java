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
				}
			}
		}else{
			messageBox.setStyle("-fx-background-color:#e74c3c");
			messageLabel.setText("Incorrect password!");
			oldPasswordField.setStyle("-fx-border-color: #e74c3c;"
					+ "-fx-border-width: 2;");
		}
		
	}
	
	@FXML
	public void cancelOnAction(ActionEvent event){
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void handleOnKeyEvent(KeyEvent event){
		oldPasswordField.setStyle("-fx-border-color: black;"
				+ "-fx-border-width:0;");
		if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim()))
			buttonUpdate.setDisable(true);
		else if((oldPasswordField.getText().trim().equals("") || newPasswordField.getText().trim().equals("")
				|| confirmPasswordField.getText().trim().equals(""))) 
			buttonUpdate.setDisable(true);
		else
			buttonUpdate.setDisable(false);
		
		if(!confirmPasswordField.getText().trim().equals("") ){
			if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim()))
				confirmPasswordField.setStyle("-fx-border-color: #e74c3c;"
						+ "-fx-border-width: 2;");
			else
				confirmPasswordField.setStyle("-fx-border-color: #2ecc71;"
						+ "-fx-border-width: 2;");
		}
		
	}
	
	@FXML
	public void handeOnMouseExited(){
		
	}
	
	public void focusGained(FocusEvent event){
		if(event.getClazz().equals("newPasswordField")){
			newPasswordField.setStyle("-fx-border-color: black;"
					+ "-fx-border-width:0;");
			messageBox.setStyle("-fx-background-color:#16a085");
			messageLabel.setText("New password have at least 6 characters.");
			
		}else{
			confirmPasswordField.setStyle("-fx-border-color: black;"
					+ "-fx-border-width:0;");
			
		}
	}
	
	public void focusLost(FocusEvent event){
		if(event.getClazz().equals("newPasswordField")){
			if(!newPasswordField.getText().matches("[a-zA-Z0-9]{6,26}+")){
				newPasswordField.setStyle("-fx-border-color: #e74c3c;"
						+ "-fx-border-width: 2;");
			}else{
				newPasswordField.setStyle("-fx-border-color: #2ecc71;"
						+ "-fx-border-width: 2;");
			}
		}else{
			if(!confirmPasswordField.getText().trim().equals(newPasswordField.getText().trim())
					|| confirmPasswordField.getText().trim().equals("")){
				confirmPasswordField.setStyle("-fx-border-color: #e74c3c;"
						+ "-fx-border-width: 2;");
			}else{
				confirmPasswordField.setStyle("-fx-border-color: #2ecc71;"
						+ "-fx-border-width: 2;");
			}
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
