/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class MenuBarFXMLController implements Initializable {

    @FXML
    Menu about1; 
    Label about2;
    Alert alert;
    
    @FXML
    public void close(){
        System.exit(0);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        about2 = new javafx.scene.control.Label("About");
        
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Author: Rafał Szyszka");
        
        about1.setGraphic(about2);
        about2.setOnMouseClicked((MouseEvent e) -> {
            alert.showAndWait();
   
        });
        
    }    
    
}
