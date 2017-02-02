package com.app.window;

import com.app.controller.StudentSubjectController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class SubjectOptionWindow {
	
	public static void display(DBManager manager){
		try{
			Stage stage = new Stage();
			stage.setTitle("Subject Option Window");
			stage.setMaximized(true);
			stage.setMinWidth(1200);
			stage.setMinHeight(800);
			
			ControllerEvent event = new ControllerEvent();
			event.setManager(manager);
			
			Initializer.addControllerListener(new StudentSubjectController());
			Initializer.callControllerListener(event);
			
			Parent root = (Parent) FXMLLoader.load(ResourceLoader.source("fxml/student_subject.fxml"));
			
			Scene scene = new Scene(root , 800 , 800);
			stage.setScene(scene);
			stage.show();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
