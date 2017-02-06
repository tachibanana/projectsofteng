package com.app.window;

import java.util.List;

import com.app.controller.GeneratedStudentController;
import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.model.Student;
import com.app.util.Initializer;
import com.app.util.ResourceLoader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneratedStudentWindow {
	
	private static Parent root;
	private static Scene scene;
	
	public static void display(DBManager manager, List<Student> list, String subjectCode){
		try{
			Stage stage = new Stage();
			stage.setTitle("Student List");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setMaximized(true);
			stage.setMinWidth(1000);
			stage.setMinHeight(600);
			
			
			
			ControllerEvent event = new ControllerEvent();
			event.setManager(manager);
			event.setAttribute(list);
			event.setClazz(subjectCode);
			
			Initializer.addControllerListener(new GeneratedStudentController());
			Initializer.callControllerListener(event);
			
			root = FXMLLoader.load(ResourceLoader.source("fxml/generated_student_list.fxml"));
			scene = new Scene(root , 810 , 320);
			scene.getStylesheets().add(ResourceLoader.source("css/student_info.css").toString());
			
			stage.setScene(scene);
			stage.showAndWait();
			
			stage.setOnCloseRequest(e->{
				scene = null;
				root = null;
				stage.close();
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
