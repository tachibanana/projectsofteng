package com.app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.app.database.DBManager;
import com.app.event.ControllerEvent;
import com.app.listener.ControllerListener;
import com.app.model.Subject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	
	private static DBManager manager;
    private static final ObservableList<Subject> data = FXCollections.observableArrayList();
	
	@Override
	public void controllerLoad(ControllerEvent event) {
		manager = (DBManager) event.getManager();
		for(Subject subject : manager.getAllSubjectByYearAndSem(1, 1)){
			data.add(subject);
			System.out.println(subject);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		yearCombo.getItems().addAll("All","1st","2nd","3rd","4th");
		semesterCombo.getItems().addAll("1","2");
		yearCombo.getSelectionModel().select(1);
		semesterCombo.getSelectionModel().select(0);
		
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
		tableView.getColumns().addAll(c2, nameColumn, descColumn, unitColumn, semColumn, prerequisiteColumn);
		
		Button button = new Button();
		button.setOnAction(event ->{
			for(Subject subject : tableView.getItems())
				System.out.println(subject.isCheck());
		});
		
		boxOfTreasure.getChildren().add(button);
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

}
