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

public class FacultyInfoWindow {
	
	public static void display(DBManager manager){
		try{
			Stage stage = new Stage();
			stage.setTitle("Student Info");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setMinWidth(810);
			stage.setMinHeight(360);
			
			ControllerEvent event = new ControllerEvent();
			event.setManager(manager);
			
			Initializer.addControllerListener(new StudentInfoController());
			Initializer.callControllerListener(event);
			
			Parent root = FXMLLoader.load(ResourceLoader.source("fxml/faculty_info.fxml"));
			Scene scene = new Scene(root , 810 , 320);
			scene.getStylesheets().add(ResourceLoader.source("css/student_info.css").toString());
			
			stage.setScene(scene);
			stage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
