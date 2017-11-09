/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import crud.LoginHistoryCRUD;
import crud.RoleCRUD;
import crud.UsersCRUD;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import others.LoginHistory;
import others.Users;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class LoginPageController implements Initializable {

    @FXML
    Label info;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    private Stage stage;
    private Scene registerScene;
    private Scene mainWindowScene;
    
    private MainWindowController mainWindowController;

    List<Users> users;
    Users user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setRegisterScene(Scene registerScene) {
        this.registerScene = registerScene;
    }

    public void setMainWindowScene(Scene mainWindowScene) {
        this.mainWindowScene = mainWindowScene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void signUpClick() {
        info.setText("");
        stage.setScene(registerScene);
    }

    public void signInClick() {
        users = UsersCRUD.getAllUsers();
        user = new Users();
        Users tmp = new Users();
        user.setName(username.getText());
        boolean found = false;
        for (Users el : users) {
            if (user.equals(el)) {
                tmp.setName(el.getName());
                tmp.setPassword(el.getPassword());
                tmp.setIdRole(el.getIdRole());
                tmp.setIdUser(el.getIdUser());
                found = true;
            }
        }
        if (!found) {
            info.setText("User does not exist.");
            return;
        }

        user.setPassword(password.getText());
        if (!user.getPassword().equals(tmp.getPassword())) {
            info.setText("Incorrect password!");
            return;
        }

        info.setText("");
        System.out.println(tmp.getIdUser());
        System.out.println(tmp.getName());
        System.out.println(tmp.getPassword());
        System.out.println(tmp.getIdRole());
        mainWindowController.ustaw(tmp);
        LoginHistoryCRUD.addLog(new LoginHistory(tmp.getIdUser(), new Date()));
        stage.setScene(mainWindowScene);
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }


}
