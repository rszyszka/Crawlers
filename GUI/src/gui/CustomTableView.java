/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Student;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Szysz
 */
public class CustomTableView extends AnchorPane {
    
        private final ObservableList<Student> studentsList;
	private final TableView<Student> tableView;
	private final TableColumn<Student, String> mark;
	private final TableColumn<Student, String> firstName;
	private final TableColumn<Student, String> lastName;
	private final TableColumn<Student, String> age;
	
	CustomTableView(){
		studentsList=FXCollections.observableArrayList();
		tableView=new TableView<>(studentsList);
                
                AnchorPane.setRightAnchor(tableView, 0.0);
                AnchorPane.setLeftAnchor(tableView, 0.0);
                AnchorPane.setTopAnchor(tableView, 0.0);
                AnchorPane.setBottomAnchor(tableView, 0.0);

		mark = new TableColumn<>("Mark");
                mark.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
                mark.setResizable(false);
		mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
				
		firstName=new TableColumn<>("First Name");
		firstName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
                firstName.setResizable(false);
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		lastName=new TableColumn<>("Last Name");
		lastName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
                lastName.setResizable(false);
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		age=new TableColumn<>("Age");
		age.prefWidthProperty().bind(tableView.widthProperty().multiply(0.195));
                age.setResizable(false);
		age.setCellValueFactory(new PropertyValueFactory<>("age"));

		tableView.getColumns().addAll(mark,firstName,lastName,age);
                
                
                
		this.getChildren().add(tableView);
	}
	
	public void addStudent(Student s){
		studentsList.add(s);
	}
	public void removeStudent(Student s){
		studentsList.remove(s);
}
}
