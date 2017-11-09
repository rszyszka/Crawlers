/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class VBoxFXMLController implements Initializable {

    @FXML private TabPaneFXMLController tabPaneController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void addStudent(Student student){
		tabPaneController.addStudent(student);
	}
	
	public void removeStudent(Student student){
		tabPaneController.removeStudent(student);
	}
    
    
}
