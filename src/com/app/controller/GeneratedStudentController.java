package com.app.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GeneratedStudentController implements ControllerListener, Initializable{

	@FXML
	private TableView<Student> table;
	@FXML
	private Label nosLabel;
	@FXML
	private Label subLabel;
	
	private static DBManager manager;
	private static List<Student> listOfStudent;
	private static String subject;
	
	private static final ObservableList<Student> data = FXCollections.observableArrayList();
	 
	@SuppressWarnings("unchecked")
	@Override
	public void controllerLoad(ControllerEvent event) {
		manager = event.getManager();
		listOfStudent = (List<Student>) event.getAttribute();
		subject = event.getClazz();
		for(Student student : listOfStudent)
			data.add(student);
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		TableColumn<Student, String> fnameCol = new TableColumn<Student, String>("First Name");
		fnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Student, String> lnameCol = new TableColumn<Student, String>("Last Name");
		lnameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Student, String> emailCol = new TableColumn<Student, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Student, Integer> snCol = new TableColumn<Student, Integer>("Student Number");
		snCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
		
		TableColumn<Student, String> codeCol = new TableColumn<Student, String>("Course Code");
		codeCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		
		TableColumn<Student, String> yearCol = new TableColumn<Student, String>("Year Code");
		yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
		
		subLabel.setText(subject);
		table.setItems(data);
		table.getColumns().addAll(snCol, fnameCol, lnameCol, emailCol,codeCol, yearCol);
		nosLabel.setText(table.getItems().size()+ "");
	}
	
	@FXML
	public void handleDoneOnAction(ActionEvent event){
		table.getItems().clear();
		((Stage) ((Node)event.getSource()).getScene().getWindow()).close();
	}

}
