package com.app.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.user.Course;
import com.app.model.user.Year;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class StudentInfoController implements ControllerListener , Initializable , ChangeListener<String> {

	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	@FXML
	private ComboBox<String> dropdownCourseList;
	@FXML
	private ComboBox<String> dropdownYearList;
	private static DBManager manager;
	private static List<Course> listOfCourse;
	private static Course selectedCourse;

	@Override
	public void controllerLoad(ControllerEvent event) {
		manager = (DBManager) event.getManager();
		listOfCourse = manager.getListOfCourse();
	}

	@Override
	public void initialize(URL url, ResourceBundle resouce) {
		if(listOfCourse != null){
			for(Course course : listOfCourse)
				dropdownCourseList.getItems().add(course.getCourseCode());
			dropdownCourseList.valueProperty().addListener(this);
		}
	}

	@Override
	public void changed(ObservableValue<? extends String> value, String oldValue, String newValue) {
		if(!(oldValue != null ? oldValue : "").equals(newValue))
			dropdownYearList.getItems().clear();
		
		if(dropdownCourseList.getSelectionModel().getSelectedIndex() > -1){
			dropdownYearList.setDisable(false);
			
			for(Course course : listOfCourse){
				if(course.getCourseCode().equals(value.getValue()))
					selectedCourse = course;
			}
			
			for(Year year : selectedCourse.getListOfYear())
				dropdownYearList.getItems().add(year.getYearCode());
		}
		
	}
	
	@FXML
	private void handleCancelOnAction(ActionEvent event){
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	
	@FXML
	private void handleSaveOnAction(ActionEvent event){
		
	}


}
