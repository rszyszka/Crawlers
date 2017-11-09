/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Logger;
import crawler.Student;
import javafx.application.Platform;

/**
 *
 * @author Szysz
 */
public class GUILogger implements Logger{
    
    CustomTabPane tabPane = CustomTabPane.getInstance();
    
    @Override
    public void log(String status, Student student) {
       
        switch(status){
            case "ADDED":
                Platform.runLater(()->{
                    tabPane.addStudent(student);
                });
                break;
            case "REMOVED":
                Platform.runLater(()->{
                    tabPane.removeStudent(student);
                });        
                break;
            default:
                break;
        }
    }
    
}
