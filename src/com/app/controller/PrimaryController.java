package com.app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.User;
import com.app.util.Initializer;
import com.app.window.ChangePasswordWindow;
import com.app.window.ConfirmWindow;
import com.app.window.StudentInfoWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PrimaryController implements Initializable, ControllerListener{

	private static User user;
	private static DBManager manager;
	private static PrimaryController primaryController;
	
	@FXML
	public void exitOnAction(ActionEvent event){
		if(ConfirmWindow.display("Are you sure your want to exit?"))
			System.exit(0);
	}
	
	@FXML
	public void changePasswordOnAction(ActionEvent event) throws IOException{
		ControllerEvent controllerEvent = new ControllerEvent();
		controllerEvent.setAttribute(user);
		controllerEvent.setManager(manager);
		controllerEvent.setClazz(ChangePasswordController.getInstance().getClass().getCanonicalName());
		
		Initializer.addControllerListener(ChangePasswordController.getInstance());
		Initializer.callControllerListener(controllerEvent);
		
		ChangePasswordWindow.display();
	}
	
	@FXML
	public synchronized void handleStudentOnAction(ActionEvent event){
		try{
			StudentInfoWindow.display(manager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void controllerLoad(ControllerEvent event) {
		if(event.getClazz().trim().equals(getInstance().getClass().getCanonicalName())){
			user = (User) event.getAttribute();
			manager = event.getManager();
		}
		
	}

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		
	}
	
	public static PrimaryController getInstance(){
		if(primaryController == null)
			primaryController = new PrimaryController();
		return primaryController;
	}
}
