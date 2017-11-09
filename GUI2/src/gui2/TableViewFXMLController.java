/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class TableViewFXMLController implements Initializable {

    private final ObservableList<Student> studentsList=FXCollections.observableArrayList();
    @FXML private TableView<Student> tableView;
    @FXML private TableColumn<Student, String> mark;
    @FXML private TableColumn<Student, String> firstName;
    @FXML private TableColumn<Student, String> lastName;
    @FXML private TableColumn<Student, String> age;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                tableView.setItems(studentsList);
            
		mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		age.setCellValueFactory(new PropertyValueFactory<>("age"));
                
                mark.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
                lastName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
                firstName.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
                age.prefWidthProperty().bind(tableView.widthProperty().multiply(0.196));
                
                
                
    }
    
    public void addStudent(Student s){
	studentsList.add(s);
	}
    public void removeStudent(Student s){
	studentsList.remove(s);
    }
    
}
