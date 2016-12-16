package com.app.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.app.database.DBLoginManager;
import com.app.event.ControllerEvent;
import com.app.event.LoginEvent;
import com.app.listener.ControllerListener;
import com.app.model.User;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable, ControllerListener{
	
	@FXML
	private Label welcomeLabel;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button buttonLogin;
	@FXML
	private ImageView imageView;
	private static DBLoginManager manager;
	private static LoginController loginController;
	
	@FXML
	public synchronized void handleOnAction(ActionEvent event) throws Exception{
		
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
		
		
//		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
//		
//		Initializer.addLoginListener(Main.getInstance());
//		Initializer.callLoginListener(loginEvent);
	
	}

	@Override
	public void controllerLoad(ControllerEvent event) {
		if(event.getClazz().trim().equals(getClass().getCanonicalName().trim())){
			manager = (DBLoginManager) event.getManager();
		}
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		try{
			Image image = new Image(new FileInputStream("imp/img/icon/spin.gif"));
			imageView.setImage(image);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static LoginController getInstance(){
		if(loginController == null)
			loginController = new LoginController();
		return loginController;
	}

}
