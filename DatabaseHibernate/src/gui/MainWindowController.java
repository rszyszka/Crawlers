/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import others.Users;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class MainWindowController implements Initializable {

    private Scene loginScene;
    private Stage stage;
    
    private Users user;
    
    @FXML ManageViewController manageViewController;
    @FXML TableViewController tableViewController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }
    
    public void addUser(Users user){
        tableViewController.addUser(user);
    }
    
    public void removeUser(Users user){
        tableViewController.removeUser(user);
    }
    
    public void ustaw(Users user){
        manageViewController.setUser(user);
        manageViewController.setMainWindowController(this);
        manageViewController.setLoginScene(loginScene);
        manageViewController.setStage(stage);

        
        System.out.println(user.getIdUser());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getIdRole());
        
        
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    
}
