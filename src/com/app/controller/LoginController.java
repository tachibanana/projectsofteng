package com.app.controller;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.event.LoginEvent;
import com.app.listener.ControllerListener;
import com.app.main.Main;
import com.app.model.Attempt;
import com.app.model.hibernate.User;
import com.app.util.Initializer;
import com.app.util.Logs;
import com.app.util.ResourceLoader;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	private VBox loadingPane;
	@FXML
	private ImageView imageView;
	@FXML
	private VBox messagePanel;
	@FXML
	private Label messageLabel;
	@FXML
	private ComboBox<String> accessTypeOption;
	private static DBManager manager;
	private static LoginController loginController;
	private static Attempt attempt;
	private static volatile Stage stage;
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		try{
			loadingPane.setVisible(false);
			imageView.setVisible(false);
			
			accessTypeOption.getItems().addAll(new String[] {"ADMIN" , "STUDENT"});
			accessTypeOption.getSelectionModel().select(0);
			
			Image image = new Image(new FileInputStream(ResourceLoader.dir() + "/imp/img/icon/loading.gif"));
			imageView.setImage(image);
			
			buttonLogin.setDisable(true);
			attempt = Logs.getLoginAttempt();
			
			usernameField.focusedProperty().addListener((ob , oldProperty , newProperty)->{
				if(newProperty){
					usernameField.setStyle("-fx-border-width:0;");
				}
			});
			
			if(attempt.getNumberOfAttempt() >= 3){
				usernameField.setDisable(true);
				passwordField.setDisable(true);
				buttonLogin.setDisable(true);
				
				messagePanel.setStyle("-fx-background-color:#d35400");
				messageLabel.setText("Too many failed login attempts. Please wait and try again.");
				addFadeAnimation(messagePanel);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@FXML
	public synchronized void handleOnAction(ActionEvent event) throws Exception{
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		User user = null;
		Boolean isSuccess = false;

		attempt.setNumberOfAttempt(attempt.getNumberOfAttempt() + 1);
		attempt.setLastAttempt(Calendar.getInstance());

		if(attempt.getNumberOfAttempt() <= 3){
			if((user = manager.getUserWithUsernameAndPassword(username, password)) != null){
				isSuccess = true;
				stage = ((Stage) ((Node)event.getSource()).getScene().getWindow());
				
				LoginEvent loginEvent = new LoginEvent();
				loginEvent.setIsSuccess(isSuccess);
				loginEvent.setUser(user);
				
				Initializer.addLoginListener(new Main());
				Initializer.callLoginListener(loginEvent);
				
				Logs.setLoginAttempt(new Attempt(0,null));

				playTransition(0);
				
			}else if(attempt.getNumberOfAttempt() == 3){
				Logs.setLoginAttempt(attempt);
				playTransition(2);
			}else if(accessTypeOption.getSelectionModel().getSelectedIndex() == 1 && 
					!usernameField.getText().matches("[0-9a-zA-Z]{3,20}+[(\\.)_]"
					+ "[0-9a-zA-Z]{3,20}+@[a-zA-Z]+\\.com")){
				
					Logs.setLoginAttempt(attempt);
				
					playTransition(3);
			}else{
				Logs.setLoginAttempt(attempt);
				playTransition(1);
			}
		}
	}

	
	@FXML
	public void handleKeyEvent(KeyEvent event){
		if(!usernameField.getText().trim().equals("") && !passwordField.getText().trim().equals(""))
			buttonLogin.setDisable(false);
		else
			buttonLogin.setDisable(true);
	}
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		if(event.getClazz().trim().equals(getClass().getCanonicalName().trim())){
			manager =  event.getManager();
			attempt = Logs.getLoginAttempt();
		}
		
	}
	
	public void addFadeAnimation(Node node){
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();
	}
	
	public void playTransition(int flag) throws InterruptedException{
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				Platform.runLater(()->{
					messagePanel.setStyle("-fx-background-color:#2ecc71");
					messageLabel.setText("Loading. . .");
					
					usernameField.setDisable(true);
					passwordField.setDisable(true);
					buttonLogin.setDisable(true);
					
					loadingPane.setVisible(true);
					imageView.setVisible(true);
				});
			}	
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try{
					t1.join();
					Thread.sleep(2000);
				}catch(InterruptedException e){e.printStackTrace();}
				Platform.runLater(()->{
					
					usernameField.setDisable(false);
					passwordField.setDisable(false);
					buttonLogin.setDisable(false);
					
					if(flag == 0){
						stage.close();
					
					}else if(flag == 1){
						messagePanel.setStyle("-fx-background-color:#e74c3c");
						messageLabel.setText("Incorrect username or password.");
						addFadeAnimation(messagePanel);
						
					}else if(flag == 2){			
						usernameField.setDisable(true);
						passwordField.setDisable(true);
						buttonLogin.setDisable(true);
						
						messagePanel.setStyle("-fx-background-color:#d35400");
						messageLabel.setText("Too many failed login attempts. Please wait and try again.");
						addFadeAnimation(messagePanel);
					}else if(flag == 3){
					
						usernameField.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
						
						messagePanel.setStyle("-fx-background-color:#e74c3c");
						messageLabel.setText("Please enter a valid email (e.g. dummy.fake@account.com");
						addFadeAnimation(messagePanel);
					}
					
					loadingPane.setVisible(false);
					imageView.setVisible(false);
				});
			}
		});
		
		t1.start();
		t2.start();
		
	}
	
	public static LoginController getInstance(){
		if(loginController == null)
			loginController = new LoginController();
		return loginController;
	}
	
}
