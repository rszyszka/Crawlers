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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import others.Users;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class ManageViewController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label infoLabel, usrInfo, passwdInfo;

    private Alert confirm;

    private Users user;
    private Scene loginScene;
    private Stage stage;

    private MainWindowController mainWindowController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void saveUserClicked() {
        String usr = username.getText();
        if (UsersValidate.validateName(usr)) {
            mainWindowController.removeUser(user);
            user.setName(usr);
            UsersCRUD.updateUser(user);
            passwdInfo.setText("");
            usrInfo.setText("");
            infoLabel.setText("Username successfully changed!");
            mainWindowController.addUser(user);
        } else {
            passwdInfo.setText("");
            infoLabel.setText("");
            usrInfo.setText("Invalid username format!");
        }

    }

    public void savePasswdClicked() {
        String pass = password.getText();
        if (UsersValidate.validatePassword(pass)) {
            user.setPassword(pass);
            UsersCRUD.updateUser(user);
            passwdInfo.setText("");
            usrInfo.setText("");
            infoLabel.setText("Password successfully changed!");
        } else {
            passwdInfo.setText("Invalid password format!");
        }

    }

    public void deleteClicked() {
        confirm = new Alert(AlertType.CONFIRMATION, "Do you really want to delete your account?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();
        if (confirm.getResult() == ButtonType.YES) {
            UsersCRUD.deleteUser(user.getIdUser());
            mainWindowController.removeUser(user);
            stage.setScene(loginScene);
        }
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

}
