/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class TabPaneFXMLController implements Initializable {

    
    @FXML TableViewFXMLController tableViewController;
    @FXML ListViewFXMLController listViewController;
    @FXML BarChartFXMLController barChartController;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void addStudent(Student s){
    
        tableViewController.addStudent(s);
        listViewController.addedStudent(s);
        barChartController.addMark(s.getMark());
    }
    public void removeStudent(Student s){
    
        tableViewController.removeStudent(s);
        listViewController.removedStudent(s);
        barChartController.removeMark(s.getMark());
    }
    
}
