/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Student;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Szysz
 */
public class CustomTabPane extends AnchorPane {
    
    private final CustomTableView students;
    private final CustomListView logs; 
    private final CustomBarChart chart;
    private final TabPane tabPane;
    
    private static CustomTabPane me=null;
    
    public CustomTabPane(){
        
        students = new CustomTableView();
        logs = new CustomListView();
        chart = new CustomBarChart();
        
        tabPane = new TabPane();
        
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        
        Tab tab1 = new Tab();
        tab1.setText("Students");
        tab1.setClosable(false);
        tab1.setContent(students);
        tabPane.getTabs().add(tab1);
        
        Tab tab2 = new Tab();
        tab2.setText("Log");
        tab2.setClosable(false);
        tab2.setContent(logs);
        tabPane.getTabs().add(tab2);
        
        Tab tab3 = new Tab();
        tab3.setText("Histogram");
        tab3.setClosable(false);
        tab3.setContent(chart);
        tabPane.getTabs().add(tab3);
        
         
        
        this.getChildren().add(tabPane);
    }
    
    public void addStudent(Student s){
    
        students.addStudent(s);
        logs.addedStudent(s);
        chart.addMark(s.getMark());
    }
    public void removeStudent(Student s){
    
        students.removeStudent(s);
        logs.removedStudent(s);
        chart.removeMark(s.getMark());
    }
    
    public static CustomTabPane getInstance(){
        if(me == null){
            me = new CustomTabPane();
        }
        return me;
    }
    
}
