package com.app.window;

import java.io.IOException;

import com.app.controller.LoginController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow {

	public static void display(DBManager manager) throws IOException{
		Stage loginStage = new Stage();
		loginStage.setTitle("Login");
		loginStage.setMinWidth(540);
		loginStage.setMinHeight(420);
		loginStage.setOnCloseRequest(e -> {
			e.consume();
			if(ConfirmWindow.display("Are you sure yout want to exit?"))
				System.exit(0);
		});
		
		Parent root = (Parent) FXMLLoader.load(ResourceLoader.source("fxml/login.fxml"));
		Scene scene = new Scene(root, 520 , 390);
		scene.getStylesheets().add(ResourceLoader.source("css/login.css").toString());
		
		ControllerEvent event = new ControllerEvent();
		event.setManager(manager);
		event.setClazz(LoginController.getInstance().getClass().getCanonicalName());
		
		Initializer.addControllerListener(LoginController.getInstance());
		Initializer.callControllerListener(event);
		
		loginStage.setScene(scene);
		loginStage.showAndWait();
	}

}
