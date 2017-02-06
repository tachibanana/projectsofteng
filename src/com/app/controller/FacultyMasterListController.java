package com.app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Course;
import com.app.model.Student;
import com.app.model.Subject;
import com.app.model.Transaction;
import com.app.model.Year;
import com.app.window.GeneratedStudentWindow;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacultyMasterListController implements ControllerListener, Initializable, ChangeListener<String>{

	@FXML
	private ComboBox<String> dropdownCourseList;
	@FXML
	private ComboBox<String> dropdownYearList;
	@FXML
	private TableView<Subject> table1;
	@FXML
	private TableView<Subject> table2;
	private static DBManager manager;
	private static List<Course> listOfCourse;
	private static Course selectedCourse;
	private static final ObservableList<Subject> dataSem1 = FXCollections.observableArrayList();
	private static final ObservableList<Subject> dataSem2 = FXCollections.observableArrayList();
	private TableColumn<Subject, String> set1;
	private TableColumn<Subject, String> set2;
	
	private boolean isTable1 = false;
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		System.out.println("tae");
		manager = event.getManager();
		listOfCourse = manager.getListOfCourse();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(listOfCourse != null){
			for(Course course : listOfCourse)
				dropdownCourseList.getItems().add(course.getCourseCode());
			dropdownCourseList.valueProperty().addListener(this);
		}
		
		dropdownYearList.valueProperty().addListener((value , newV, oldV)->{
			if(dropdownYearList.getSelectionModel().getSelectedIndex() > -1){
				updateTable();
			}
		});
		
		table1.focusedProperty().addListener((observer , oldValue , newValue)->{
			if(newValue){
				for(int ctr = 0; ctr < table1.getItems().size(); ctr++)
					table2.getSelectionModel().clearSelection(ctr);
				isTable1 = true;
			}
		});
		
		table2.focusedProperty().addListener((observer , oldValue , newValue)->{
			if(newValue){
				for(int ctr = 0; ctr < table1.getItems().size(); ctr++)
					table1.getSelectionModel().clearSelection(ctr);
				isTable1 = false;
			}
			
		});
		
		initTable();
		
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
			
			dropdownYearList.getSelectionModel().select(0);

		}
		
		updateTable();
		
	}
	
	public void updateTable(){
		clearTable();
		
		for(Subject subject : manager
				.getAllSubjectByYearSemeterAndCourseId(dropdownYearList.getSelectionModel().getSelectedIndex() + 1,
						1, 
						manager.getCourseByCode(
								dropdownCourseList.getSelectionModel().getSelectedItem()).getCourseId())){
			table1.getItems().add(subject);
		}
		
		for(Subject subject : manager
				.getAllSubjectByYearSemeterAndCourseId(dropdownYearList.getSelectionModel().getSelectedIndex() + 1,
						2, 
						manager.getCourseByCode(
								dropdownCourseList.getSelectionModel().getSelectedItem()).getCourseId())){
			table2.getItems().add(subject);
		}
	}
	
	public void initTable(){
		set1 = new TableColumn<Subject, String>("Subject");
		set1.setCellValueFactory(new PropertyValueFactory<>("id"));
		set1.setMaxWidth(200);
		
		TableColumn<Subject, String> set11 = new TableColumn<Subject, String>("Description");
		set11.setCellValueFactory(new PropertyValueFactory<>("description"));
		set11.setPrefWidth(200);
		
		set2 = new TableColumn<Subject, String>("Subject");
		set2.setCellValueFactory(new PropertyValueFactory<Subject, String>("id"));
		set2.setMaxWidth(200);
		
		TableColumn<Subject, String> set22 = new TableColumn<Subject, String>("Description");
		set22.setCellValueFactory(new PropertyValueFactory<>("description"));
		set22.setPrefWidth(200);
		
		table1.setItems(dataSem1);
		table1.getColumns().add(set1);
		table1.getColumns().add(set11);
		
		table2.setItems(dataSem2);
		table2.getColumns().add(set2);
		table2.getColumns().add(set22);
		
		table1.setEditable(true);
		table2.setEditable(true);
	}
	
	public void clearTable(){
		table1.getItems().clear();
		table2.getItems().clear();
	}
	
	@FXML
	public void handleGenerateOnAction(ActionEvent event){

		Subject subject = null;
		List<Student> studentList = new ArrayList<Student>();
		
		if(isTable1){
			subject = table1.getSelectionModel().getSelectedItem();
		}else{
			subject = table2.getSelectionModel().getSelectedItem();
		}
		
		if(subject != null){
			for(Transaction t : manager.getAllTransactionBySubjectId(subject.getId())){
	
				Student personalInfo = (Student) manager.getPersonByStudentInfo(String.valueOf(t.getUserId()));
				Student student = manager.getStudentById((int)t.getUserId());
				student.setFirstName(personalInfo.getFirstName());
				student.setLastName(personalInfo.getLastName());
				student.setMiddleName(personalInfo.getMiddleName());
				
				student.setYear(manager.getYearById(student.getYear(),
						student.getCourse()).getYearCode());
				System.out.println();
				
				student.setCourse(manager.getCourseById(student.getCourse()).getCourseCode());
				studentList.add(student);
			}
			
			GeneratedStudentWindow.display(manager, studentList, subject.getId());
		}else
			JOptionPane.showMessageDialog(null,"Select a subject.");
	}

}
