package com.app.window;

import java.io.IOException;
import java.net.URL;

import com.app.main.Main;

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
		
		Parent root = FXMLLoader.load(new URL(Main.getInstance().DIRECTORY_PATH + "/imp/fxml/change_password.fxml"));
		
		Scene scene = new Scene(root ,443.0 , 250.0);
		scene.getStylesheets().add(Main.getInstance().DIRECTORY_PATH + "/imp/css/change_password.css");
		
		stage.setScene(scene);
		stage.showAndWait();
	}

}
