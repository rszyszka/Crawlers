/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import crud.UsersCRUD;
import crud.UsersValidate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import others.Users;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class RegisterPageController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton adminButton;
    @FXML
    private RadioButton userButton;

    private ToggleGroup group;
    private Stage stage;
    private Scene loginScene;
    
    UsersCRUD userCrud = new UsersCRUD();
    
    private MainWindowController mainWindowController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        group = new ToggleGroup();
        adminButton.setToggleGroup(group);
        userButton.setToggleGroup(group);
        userButton.setSelected(true);
    }

    public void saveClick() {
        Users user = new Users();
        user.setName(username.getText());
        user.setPassword(password.getText());
        if(!UsersValidate.validateUser(user)){
            info.setText("Invalid username or password format!");
            return;
        }
        if(adminButton.isSelected())
            user.setIdRole(1);
        else
            user.setIdRole(2);
        
        userCrud.addUser(user);
        mainWindowController.addUser(user);
        stage.setScene(loginScene);
    }

    public void cancelClick() {
        stage.setScene(loginScene);
    }

    public void clearClick() {
        info.setText("");
        username.setText("");
        password.setText("");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

}
