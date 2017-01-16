package com.app.window;

import com.app.controller.StudentInfoController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class StudentInfoWindow {
	
	public static void display(DBManager manager){
		try{
			Stage stage = new Stage();
			stage.setTitle("Student Info");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setMinWidth(870);
			stage.setMinHeight(400);
			
			ControllerEvent event = new ControllerEvent();
			event.setManager(manager);
			
			Initializer.addControllerListener(new StudentInfoController());
			Initializer.callControllerListener(event);
			
			Parent root = FXMLLoader.load(ResourceLoader.source("fxml/student_info.fxml"));
			Scene scene = new Scene(root , 880 , 400);
			scene.getStylesheets().add(ResourceLoader.source("css/student_info.css").toString());
			
			stage.setScene(scene);
			stage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
