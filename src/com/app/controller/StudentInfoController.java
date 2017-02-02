package com.app.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Course;
import com.app.model.Student;
import com.app.model.Year;
import com.app.util.PasswordGenerator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentInfoController implements ControllerListener , Initializable , ChangeListener<String> {

	@FXML
	private TextField studentNumberTextfield;
	@FXML
	private TextField lastnameTextfield;
	@FXML
	private TextField firstnameTextfield;
	@FXML
	private TextField middlenameTextfield;
	@FXML
	private TextField emailTextfield;
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	@FXML
	private ComboBox<String> dropdownCourseList;
	@FXML
	private ComboBox<String> dropdownYearList;
	@FXML
	private VBox messageBox;
	@FXML
	private Label messageLabel;
	@FXML
	private Label messageStudentNumber;
	@FXML
	private Label messageStudentName;
	@FXML
	private Label messageEmail;
	@FXML
	private Label messageCourse;
	@FXML
	private Label messageYear;
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
		
		studentNumberTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageStudentNumber.setVisible(false);
			studentNumberTextfield.setStyle("-fx-border-width:0;");
		});
		
		lastnameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageStudentName.setVisible(false);
			lastnameTextfield.setStyle("-fx-border-width:0;");
		});
		
		firstnameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageStudentName.setVisible(false);
			firstnameTextfield.setStyle("-fx-border-width:0;");
		});
		
		middlenameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageStudentName.setVisible(false);
			middlenameTextfield.setStyle("-fx-border-width:0;");
		});
		
		emailTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmail.setVisible(false);
			emailTextfield.setStyle("-fx-border-width:0;");
		});
		
		dropdownCourseList.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageCourse.setVisible(false);
			dropdownCourseList.setStyle("-fx-border-width:0;");
		});
		
		dropdownYearList.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageYear.setVisible(false);
			dropdownYearList.setStyle("-fx-border-width:0;");
		});
		
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
	private synchronized void handleSaveOnAction(ActionEvent event) throws InterruptedException{
	
		try{
			boolean isSuccess = true;
			
			if(!studentNumberTextfield.getText().trim().equals("")){
				//not empty
				if(studentNumberTextfield.getText().matches("[0-9]+")){
					//valid input
					studentNumberTextfield.setStyle("-fx-border-color:#2ecc71;-fx-border-width: 2;");
				}else{
					//not numeric
					studentNumberTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
					isSuccess = false;
					
					messageBox.setStyle("-fx-background-color:#e74c3c");
					messageLabel.setText("There was a problem with your submission.");
					
					messageStudentNumber.setVisible(true);
					messageStudentNumber.setText("can only contain numbers");	
					
				}
			}else{
				//empty
				studentNumberTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageStudentNumber.setVisible(true);
				messageStudentNumber.setText("student number is required");
				
			}
			
			if(!emailTextfield.getText().trim().equals("")){
				//not empty
				if(emailTextfield.getText()
						.matches("[0-9a-zA-Z]{3,20}+[(\\.)_]"
								+ "[0-9a-zA-Z]{3,20}+@[a-zA-Z]+\\.com")){
					//valid input
					emailTextfield.setStyle("-fx-border-color:#2ecc71;-fx-border-width: 2;");
				}else{
					//not valid
					emailTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
					isSuccess = false;
					
					messageBox.setStyle("-fx-background-color:#e74c3c");
					messageLabel.setText("There was a problem with your submission.");
					
					messageEmail.setVisible(true);
					messageEmail.setText("please enter a valid email");	
				}
			}else{
				//empty
				emailTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageEmail.setVisible(true);
				messageEmail.setText("email address is required");
			}
			
			if(dropdownCourseList.getSelectionModel().getSelectedIndex() < 0){
				dropdownCourseList.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageCourse.setVisible(true);
				messageCourse.setText("please select a course");
			}
			
			if(dropdownYearList.getSelectionModel().getSelectedIndex() < 0 && !dropdownYearList.isDisabled()){
				dropdownYearList.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageYear.setVisible(true);
				messageYear.setText("please select a year");
			}
			
			if(!lastnameTextfield.getText().trim().equals("")){
				
			}else{
				lastnameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageStudentName.setVisible(true);
				messageStudentName.setText("student name is required");
				
				
			}if(!firstnameTextfield.getText().trim().equals("")){
					
			}else{
				firstnameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageStudentName.setVisible(true);
				messageStudentName.setText("student name is required");
			}if(!middlenameTextfield.getText().trim().equals("")){
				
			}else{
				middlenameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageStudentName.setVisible(true);
				messageStudentName.setText("student name is required");
			}
			
			if(isSuccess){
				Student student = new Student();
				
				student.setLastName(lastnameTextfield.getText());
				student.setFirstName(firstnameTextfield.getText());
				student.setMiddleName(middlenameTextfield.getText());
				
				student.setStudentNumber(Integer.parseInt(studentNumberTextfield.getText()));
				student.setEmail(emailTextfield.getText());
				student.setCourse(manager
						.getCourseByCode(dropdownCourseList
								.getSelectionModel()
								.getSelectedItem()).getCourseId());
				student.setYear(manager.getYearByCode(dropdownYearList.getSelectionModel().getSelectedItem(), manager
						.getCourseByCode(dropdownCourseList
								.getSelectionModel()
								.getSelectedItem()).getCourseId()).getYearId());
				
				String personId =  PasswordGenerator.generate("PID", manager);
				String userId =  PasswordGenerator.generate("UID", manager);
				
				student.setUsername(student.getEmail());
				student.setPassword(student.getStudentNumber() + "");
				student.setAccessType("STUDENT");
				student.setActivate(true);
				
				
				manager.saveStudent(student);
				manager.savePerson(student,personId, studentNumberTextfield.getText(), "NONE");
				manager.saveUser(student, userId, personId);
				
				lastnameTextfield.setDisable(true);
				firstnameTextfield.setDisable(true);
				middlenameTextfield.setDisable(true);
				
				studentNumberTextfield.setDisable(true);
				emailTextfield.setDisable(true);
				dropdownCourseList.setDisable(true);
				dropdownYearList.setDisable(true);
				
				buttonSave.setDisable(true);
				buttonCancel.setText("Close");
				
				messageBox.setStyle("-fx-background-color:#2ecc71");
				messageLabel.setText("You have successfully added a student");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}


}
