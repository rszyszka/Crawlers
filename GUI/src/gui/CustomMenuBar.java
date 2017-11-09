/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Szysz
 */
public class CustomMenuBar extends MenuBar {
      
    Menu prog, about1;
    Label about2;
    MenuItem close, add, remove;
    MenuBar menuBar;
    SeparatorMenuItem s;
    
    Student student;
    GUILogger log;
    
    CustomMenuBar(){
        student = new Student();
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setAge(22);
        student.setMark(4.0);
        
        log = new GUILogger();
        menuBar = new MenuBar();
        
        prog = new Menu("Program");
        about1 = new Menu();
        about2 = new Label("About");
        close = new MenuItem("Close");
        add = new MenuItem("Add Student");
        add.setDisable(true);
        remove = new MenuItem("Remove Student");
        remove.setDisable(true);
        s=new SeparatorMenuItem();
        prog.getItems().addAll(add,remove,s,close);
        
        close.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        close.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
 
        about1.setGraphic(about2);
        about2.setOnMouseClicked((MouseEvent e) -> {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setContentText("Author: Rafał Szyszka");

            alert.showAndWait();
   
        });
        
        add.setOnAction((ActionEvent e)->{
            log.log("ADDED", student);
        });
        remove.setOnAction((ActionEvent e)->{
            log.log("REMOVED", student);
        });

        this.getChildren().add(menuBar);
    }
}
