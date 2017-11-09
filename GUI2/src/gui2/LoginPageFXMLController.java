/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class LoginPageFXMLController implements Initializable {

    
    private List<User> users;
    private final User user = new User();
    private Stage stage;
    private Scene loginScene;
    private Scene mainWindowScene;
    private Scene registerScene;
    
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label info;

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public void setMainWindowScene(Scene mainWindowScene) {
        this.mainWindowScene = mainWindowScene;
    }

    public void setRegisterScene(Scene registerScene) {
        this.registerScene = registerScene;
    }
    
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void signInClick(){
        Properties p = new Properties();
        try {
            InputStream input = new FileInputStream("config.ini");
             p.load(input);
        } catch (IOException ex) {
            Logger.getLogger(LoginPageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        byte[] hashed = Base64.getDecoder().decode(p.getProperty("password"));
        byte[] salt = new byte[16];
        byte[] pass = new byte[hashed.length-salt.length];
        System.arraycopy(hashed, hashed.length-salt.length, salt, 0, salt.length);
        System.arraycopy(hashed, 0, pass, 0, pass.length);
        user.setSalt(salt);
        user.setUsername(username.getText());
        user.setPassword(password.getText());

        Base64.Encoder enc = Base64.getEncoder();
        
        if (p.getProperty("username").equals(user.getUsername()) && enc.encodeToString(pass).equals(enc.encodeToString(user.getPassword()))){
            stage.setScene(mainWindowScene);
        }
            else{
		info.setText("!!! Incorrect username or password !!!");
            }
    }
    
    public void singUpClick(){
        stage.setScene(registerScene);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
