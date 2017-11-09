/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Student;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Szysz
 */
public class CustomListView extends AnchorPane {
    
    private final ObservableList<String> log = FXCollections.observableArrayList();
    
    private final Calendar now;
    private final String time;
	
	CustomListView(){
		ListView<String> list = new ListView<>(log);
                AnchorPane.setRightAnchor(list, 0.0);
                AnchorPane.setLeftAnchor(list, 0.0);
                AnchorPane.setTopAnchor(list, 0.0);
                AnchorPane.setBottomAnchor(list, 0.0);
		this.getChildren().add(list);
                 
                now = Calendar.getInstance();
                time = String.format("%1$tH:%1$tM:%1$tS.%1$tL", now);
                 
	}
	public void addedStudent(Student s){
		log.add(time+"  [NEW]  mark: "+s.getMark()+", name: "+s.getFirstName()+" "+s.getLastName()+", age: "+s.getAge());
	}
	public void removedStudent(Student s){
		log.add(time+"  [REMOVED]  mark: "+s.getMark()+", name: "+s.getFirstName()+" "+s.getLastName()+", age: "+s.getAge());
	}
	
    
}
