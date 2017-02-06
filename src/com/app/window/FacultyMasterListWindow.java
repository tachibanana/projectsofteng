package com.app.window;

import com.app.controller.FacultyMasterListController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FacultyMasterListWindow {

	public static void display(DBManager manager){
		try{
			Stage stage = new Stage();
			stage.setTitle("Faculty Info");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setMaximized(true);
			stage.setMinWidth(1000);
			stage.setMinHeight(600);
			
			ControllerEvent event = new ControllerEvent();
			event.setManager(manager);
			
			Initializer.addControllerListener(new FacultyMasterListController());
			Initializer.callControllerListener(event);
			
			Parent root = FXMLLoader.load(ResourceLoader.source("fxml/faculty_master.fxml"));
			Scene scene = new Scene(root , 810 , 320);
			scene.getStylesheets().add(ResourceLoader.source("css/student_info.css").toString());
			
			stage.setScene(scene);
			stage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
