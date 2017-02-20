package com.app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Student;
import com.app.model.Subject;
import com.app.model.Transaction;
import com.app.model.Year;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class StudentSubjectController implements ControllerListener, Initializable{

	@FXML
	private TableView<Subject> tableView;
	@FXML
	private VBox boxOfTreasure;
	@FXML
	private ComboBox<String> yearCombo;
	@FXML
	private ComboBox<String> semesterCombo;
	@FXML
	private Accordion accordionCourseList;
	@FXML
	private TitledPane bsitTitledPane;
	@FXML
	private Label labelCourse;
	
	private static DBManager manager;
    private static final ObservableList<Subject> data = FXCollections.observableArrayList();
    private static Student user;
    private String courseId = "CID01";
    private int year = 1;
    private int sem = 1;
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		manager = (DBManager) event.getManager();
		user = (Student) event.getAttribute();
		user.setStudentNumber(manager.getStudentNumberByPerson(manager.getPersonIdByUser(user.getId())));
		
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 1 , "CID01")){
			data.add(subject);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		yearCombo.getItems().addAll("1st","2nd","3rd","4th");
		semesterCombo.getItems().addAll("1","2");
		yearCombo.getSelectionModel().select(0);
		semesterCombo.getSelectionModel().select(0);
		
		accordionCourseList.setExpandedPane(bsitTitledPane);
		TableColumn<Subject,Boolean> c2 = new TableColumn<Subject,Boolean>("#");
		c2.setCellValueFactory(new PropertyValueFactory<Subject,Boolean>("check"));
		c2.setCellFactory(col -> {
            CheckBoxTableCell<Subject, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty check = new SimpleBooleanProperty(tableView.getItems().get(index).isCheck());
                check.addListener((obs, wasActive, isNowActive) -> {
                    Subject subject = tableView.getItems().get(index);
                    subject.setCheck(isNowActive);
                });
                return check ;
            });
            return cell ;
        });
		
		TableColumn<Subject, String> nameColumn = new TableColumn<>("Subject");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Subject, String> cIColumn = new TableColumn<>("Course ID");
		cIColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
		
		TableColumn<Subject, String> descColumn = new TableColumn<>("Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableColumn<Subject, Integer> unitColumn = new TableColumn<>("Unit");
		unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
		
		TableColumn<Subject, Integer> semColumn = new TableColumn<>("Semester");
		semColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
		
		TableColumn<Subject, Integer> prerequisiteColumn = new TableColumn<>("Prerequisite");
		prerequisiteColumn.setCellValueFactory(new PropertyValueFactory<>("prerequisite"));
		
		TableColumn<Subject, Boolean> checkColumn = new TableColumn<>("#");
		checkColumn.setCellValueFactory(new PropertyValueFactory<>("check"));
		
		tableView.setItems(data);
		tableView.setEditable(true);
		tableView.getColumns().addAll(c2, nameColumn, descColumn, unitColumn, semColumn, prerequisiteColumn, cIColumn);
		
		Button button = new Button();
		button.setOnAction(event ->{
			for(Subject subject : tableView.getItems())
				System.out.println(subject.isCheck());
		});
		
		boxOfTreasure.getChildren().add(button);
		yearCombo.valueProperty().addListener((value , newV, oldV) ->{
			data.clear();
			year = yearCombo.getSelectionModel().getSelectedIndex() + 1;
			for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(year , sem , courseId)){
				data.add(subject);
			}
				
		});
		
		semesterCombo.valueProperty().addListener((value , newV, oldV) ->{
			data.clear();
			sem = semesterCombo.getSelectionModel().getSelectedIndex() + 1;
			for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(year , sem , courseId)){
				data.add(subject);
			}
				
		});
	}
	
	public TableColumn<Subject, Boolean> createCheckBoxColumn(){
		
		TableColumn<Subject,Boolean> column = new TableColumn<Subject,Boolean>("#");
		column.setCellValueFactory(new PropertyValueFactory<Subject, Boolean>("check"));
		
		column.setCellFactory(col -> {
            CheckBoxTableCell<Subject, Boolean> cell = new CheckBoxTableCell<>(index -> {
                BooleanProperty check = new SimpleBooleanProperty(tableView.getItems().get(index).isCheck());
                check.addListener((obs, wasActive, isNowActive) -> {
                    Subject subject = tableView.getItems().get(index);
                    subject.setCheck(isNowActive);
                });
                return check ;
            });
            return cell ;
        });
		return column;
	}
	
	@FXML
	public void handleAddonAction(ActionEvent event){
		Transaction t = null;
		boolean isThere = false;
		for(Subject subject : tableView.getItems()){
			if(subject.isCheck()){
				isThere = true;
				t = new Transaction(user.getStudentNumber(), subject.getId(), "ONGOING");
			
				if(t != null){
					manager.addTransaction(t);
					System.out.println(t.getSubjectId());
				}
			}

		}
		
		if(isThere)
			JOptionPane.showMessageDialog(null,"You add something");
		else
			JOptionPane.showMessageDialog(null,"Please select a subject(s)", null , JOptionPane.ERROR_MESSAGE);
			
	}
	
	@FXML
	public void handleMouseClickedBSIT(){
		data.clear();
		courseId = "CID01";
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 1 , courseId)){
			data.add(subject);
		}
		
		labelCourse.setText("Bachelor of Science in Information Technology");
		
		yearCombo.getItems().clear();
		for(Year year : manager.getListOfYearByCourseId(courseId))
			yearCombo.getItems().add(year.getYearCode());
		
		yearCombo.getSelectionModel().select(0);
		semesterCombo.getSelectionModel().select(0);
	}
	
	
	@FXML
	public void handleMouseClickedBSCEP(){
		data.clear();
		courseId = "CID02";
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 1 , courseId)){
			data.add(subject);
		}
		
		labelCourse.setText("Bachelor of Science in CpE");
		
		yearCombo.getItems().clear();
		for(Year year : manager.getListOfYearByCourseId(courseId))
			yearCombo.getItems().add(year.getYearCode());
		
		yearCombo.getSelectionModel().select(0);
		semesterCombo.getSelectionModel().select(0);
	}
	
	@FXML
	public void handleMouseClickedBSIS(){
		data.clear();
		courseId = "CID03";
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 1 , courseId)){
			data.add(subject);
		}
		
		labelCourse.setText("Bachelor of Science in Information System");
		
		yearCombo.getItems().clear();
		
		for(Year year : manager.getListOfYearByCourseId(courseId))
			yearCombo.getItems().add(year.getYearCode());
		
		yearCombo.getSelectionModel().select(0);
		semesterCombo.getSelectionModel().select(0);
	}
	
	@FXML
	public void onbtn1Year1Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 1 , courseId)){
			data.add(subject);
		}
	}

	@FXML
	public void onbtn1Year2Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(1, 2 , courseId)){
			data.add(subject);
		}
	}
	
	@FXML
	public void onbtn2Year1Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(2, 1 , courseId)){
			data.add(subject);
		}
	}

	@FXML
	public void onbtn2Year2Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(2, 2 , courseId)){
			data.add(subject);
		}
	}
	
	@FXML
	public void onbtn3Year1Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(3, 1 , courseId)){
			data.add(subject);
		}
	}

	@FXML
	public void onbtn3Year2Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(3, 2 , courseId)){
			data.add(subject);
		}
	}
	
	@FXML
	public void onbtn4Year1Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(4, 1 , courseId)){
			data.add(subject);
		}
	}

	@FXML
	public void onbtn4Year2Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(4, 2 , courseId)){
			data.add(subject);
		}
	}
	
	@FXML
	public void onbtn5Year1Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(5, 1 , courseId)){
			data.add(subject);
		}
	}

	@FXML
	public void onbtn5Year2Sem(){
		data.clear();
		for(Subject subject : manager.getAllSubjectByYearSemeterAndCourseId(5, 2 , courseId)){
			data.add(subject);
		}
	}
}
