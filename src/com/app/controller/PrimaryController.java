package com.app.controller;

import java.io.IOException;

import com.app.window.ChangePasswordWindow;
import com.app.window.ConfirmWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

	@FXML
	public void exitOnAction(ActionEvent event){
		if(ConfirmWindow.display("Are you sure your want to exit?"))
			System.exit(0);
	}
	
	@FXML
	public void changePasswordOnAction(ActionEvent event) throws IOException{
		ChangePasswordWindow.display();
	}
}
