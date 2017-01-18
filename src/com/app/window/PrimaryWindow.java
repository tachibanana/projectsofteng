package com.app.window;

import java.io.IOException;

import com.app.controller.PrimaryController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.model.hibernate.User;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryWindow {
	
	private static Stage stage;
	
	public static void display(Stage stage,DBManager manager, User user) throws IOException{
		
		ControllerEvent event = new ControllerEvent();
		event.setManager(manager);
		event.setClazz(PrimaryController.getInstance().getClass().getCanonicalName());
		event.setAttribute(user);
		
		Initializer.addControllerListener(PrimaryController.getInstance());
		Initializer.callControllerListener(event);
		
		PrimaryWindow.stage = stage;
		PrimaryWindow.stage.setMaximized(true);
		PrimaryWindow.stage.setOnCloseRequest(e ->{
			e.consume();
			if(ConfirmWindow.display("Are you sure you want to exit?"))
				PrimaryWindow.stage.close();
		});
		
		Parent root = FXMLLoader.load(ResourceLoader.source("fxml/primary.fxml"));
		Scene scene = new Scene(root,800,600);
		
		PrimaryWindow.stage.setScene(scene);
		PrimaryWindow.stage.show();
	}

}
