/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Student;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class ListViewFXMLController implements Initializable {

    private final ObservableList<String> log = FXCollections.observableArrayList();
    
    private Calendar now;
    private String time;
    
    @FXML ListView<String> listView = new ListView<>(log);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        now = Calendar.getInstance();
        time = String.format("%1$tH:%1$tM:%1$tS.%1$tL", now);   
        listView.setItems(log);
    }    
    
    public void addedStudent(Student s){
	log.add(time+"  [NEW]  mark: "+s.getMark()+", name: "+s.getFirstName()+" "+s.getLastName()+", age: "+s.getAge());
	}
    public void removedStudent(Student s){
	log.add(time+"  [REMOVED]  mark: "+s.getMark()+", name: "+s.getFirstName()+" "+s.getLastName()+", age: "+s.getAge());
	}
    
}
