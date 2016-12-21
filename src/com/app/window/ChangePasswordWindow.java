package com.app.window;

import java.io.IOException;

import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePasswordWindow {
	
	public static void display() throws IOException{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setMaximized(false);
		
		Parent root = FXMLLoader.load(ResourceLoader.source("fxml/change_password.fxml"));
		
		Scene scene = new Scene(root ,433.0 , 240.0);
		scene.getStylesheets().add(ResourceLoader.source("css/change_password.css").toString());
		
		stage.setScene(scene);
		stage.showAndWait();
	}

}
