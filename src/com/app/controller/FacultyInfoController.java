package com.app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Employee;
import com.app.util.PasswordGenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FacultyInfoController implements ControllerListener, Initializable{
	
	@FXML
	private TextField employeeNumberTextfield;
	@FXML
	private TextField lastnameTextfield;
	@FXML
	private TextField firstnameTextfield;
	@FXML
	private TextField middlenameTextfield;
	@FXML
	private TextField emailTextfield;
	@FXML
	private Label messageEmployeeNumber;
	@FXML
	private Label messageEmployeeName;
	@FXML
	private Label messageEmail;
	@FXML
	private VBox messageBox;
	@FXML
	private Label messageLabel;
	@FXML
	private Button buttonSave;
	@FXML
	private Button buttonCancel;
	private static DBManager manager;
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		manager = (DBManager) event.getManager();
	}
	
	@FXML
	public void handleSaveOnAction(ActionEvent event){
		try{
			boolean isSuccess = true;
			
			if(!employeeNumberTextfield.getText().trim().equals("")){
				//not empty
				if(employeeNumberTextfield.getText().matches("[0-9]+")){
					//valid input
					employeeNumberTextfield.setStyle("-fx-border-color:#2ecc71;-fx-border-width: 2;");
				}else{
					//not numeric
					employeeNumberTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
					isSuccess = false;
					
					messageBox.setStyle("-fx-background-color:#e74c3c");
					messageLabel.setText("There was a problem with your submission.");
					
					messageEmployeeNumber.setVisible(true);
					messageEmployeeNumber.setText("can only contain numbers");	
					
				}
			}else{
				//empty
				employeeNumberTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 2;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageEmployeeNumber.setVisible(true);
				messageEmployeeNumber.setText("employee number is required");
				
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
			
			if(!lastnameTextfield.getText().trim().equals("")){
				
			}else{
				lastnameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageEmployeeName.setVisible(true);
				messageEmployeeName.setText("employee name is required");
				
				
			}if(!firstnameTextfield.getText().trim().equals("")){
					
			}else{
				firstnameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageEmployeeName.setVisible(true);
				messageEmployeeName.setText("employee name is required");
			}if(!middlenameTextfield.getText().trim().equals("")){
				
			}else{
				middlenameTextfield.setStyle("-fx-border-color: #e74c3c;-fx-border-width: 1;");
				isSuccess = false;
				
				messageBox.setStyle("-fx-background-color:#e74c3c");
				messageLabel.setText("There was a problem with your submission.");
				
				messageEmployeeName.setVisible(true);
				messageEmployeeName.setText("employee name is required");
			}
			
			if(isSuccess){
				Employee employee = new Employee(0, null);
				
				employee.setLastName(lastnameTextfield.getText());
				employee.setFirstName(firstnameTextfield.getText());
				employee.setMiddleName(middlenameTextfield.getText());
				
				employee.setEmployeeNumber(Integer.parseInt(employeeNumberTextfield.getText()));
				employee.setEmail(emailTextfield.getText());
				
				String personId =  PasswordGenerator.generate("PID", manager);
				String userId =  PasswordGenerator.generate("UID", manager);
				
				employee.setUsername(employee.getEmail());
				employee.setPassword(employee.getEmployeeNumber() + "");
				employee.setAccessType("EMPLOYEE");
				employee.setActivate(true);
				
				
				manager.saveEmployee(employee);
				manager.savePerson(employee,personId,"NONE", employeeNumberTextfield.getText());
				manager.saveUser(employee, userId, personId);
				
				lastnameTextfield.setDisable(true);
				firstnameTextfield.setDisable(true);
				middlenameTextfield.setDisable(true);
				
				employeeNumberTextfield.setDisable(true);
				emailTextfield.setDisable(true);
				
				buttonSave.setDisable(true);
				buttonCancel.setText("Close");
				
				messageBox.setStyle("-fx-background-color:#2ecc71");
				messageLabel.setText("You have successfully added a employee");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleCancelOnAction(ActionEvent event){
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		employeeNumberTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmployeeNumber.setVisible(false);
			employeeNumberTextfield.setStyle("-fx-border-width:0;");
		});
		
		lastnameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmployeeName.setVisible(false);
			lastnameTextfield.setStyle("-fx-border-width:0;");
		});
		
		firstnameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmployeeName.setVisible(false);
			firstnameTextfield.setStyle("-fx-border-width:0;");
		});
		
		middlenameTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmployeeName.setVisible(false);
			middlenameTextfield.setStyle("-fx-border-width:0;");
		});
		
		emailTextfield.focusedProperty().addListener((observer , oldValue , newValue)->{
			messageEmail.setVisible(false);
			emailTextfield.setStyle("-fx-border-width:0;");
		});
		
		
	}

}
