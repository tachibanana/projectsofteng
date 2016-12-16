package com.app.controller;

import com.app.database.DBLoginManager;
import com.app.event.ControllerEvent;
import com.app.event.LoginEvent;
import com.app.listener.ControllerListener;
import com.app.main.Main;
import com.app.model.User;
import com.app.util.Initializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements ControllerListener{
	
	@FXML
	private Label welcomeLabel;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button buttonLogin;
	private static DBLoginManager manager;
	private static LoginController loginController;
	
	public static LoginController getInstance(){
		if(loginController == null)
			loginController = new LoginController();
		return loginController;
	}
	
	@FXML
	public void handleOnAction(ActionEvent event){
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		User user = null;
		Boolean isSuccess = false;
		
		if((user = manager.getUserWithUsernameAndPassword(username, password)) != null){
			System.out.println("Hello " + user.getFirstName());
			isSuccess = true;
		}else{
			System.out.println("Invalid");
		}
		
		LoginEvent loginEvent = new LoginEvent();
		loginEvent.setIsSuccess(isSuccess);
		loginEvent.setUser(user);
		
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
		
		Initializer.addLoginListener(Main.getInstance());
		Initializer.callLoginListener(loginEvent);
	
	}

	@Override
	public void controllerLoad(ControllerEvent event) {
		if(event.getClazz().trim().equals(getClass().getCanonicalName().trim())){
			manager = (DBLoginManager) event.getManager();
		}
		
	}


}
