package com.app.window;

import java.io.IOException;
import java.net.URL;

import com.app.controller.ConfigController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.main.Main;
import com.app.util.Initializer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConfigWindow {
	
	public static void display(DBManager manager) throws IOException{
		Stage configStage = new Stage();
		configStage.setTitle("Database Configuration");
		configStage.setResizable(false);
		configStage.setMaximized(false);
		configStage.setOnCloseRequest(e -> System.exit(0));
		
		Parent root = FXMLLoader.load(new URL(Main.getInstance().DIRECTORY_PATH + "/imp/fxml/config.fxml"));
		
		Scene scene = new Scene(root, 350.0 , 320.0);
		scene.getStylesheets().add(Main.getInstance().DIRECTORY_PATH + "/imp/css/config.css");
		
		ControllerEvent event = new ControllerEvent();
		event.setManager(manager);
		event.setClazz(ConfigController.getInstance().getClass().getCanonicalName());
		
		Initializer.addControllerListener(ConfigController.getInstance());
		Initializer.callControllerListener(event);
		
		configStage.setScene(scene);
		configStage.showAndWait();
	}

}
