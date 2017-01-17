package com.app.window;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class SubjectOptionWindow {
	
	public static void display(){
		try{
			Stage stage = new Stage();
			stage.setTitle("Subject Option Window");
			stage.setMaximized(true);
			
			VBox box = new VBox();
			Label label = new Label("Subject Option Window");
			box.getChildren().add(label);
			
			Scene scene = new Scene(box , 400 , 400);
			stage.setScene(scene);
			stage.show();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
