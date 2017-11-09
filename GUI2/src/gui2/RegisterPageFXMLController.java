/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class RegisterPageFXMLController implements Initializable {

    
    @FXML private Label label;
    @FXML private TextField usernameReg;
    @FXML private PasswordField passwordReg;
    @FXML private TextField addressReg;
    @FXML private TextField ageReg;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
 
    private ToggleGroup group;
    
    private List<User> users;
    private final User user = new User();
    
    private Stage stage;
    private Scene loginScene;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }
    
    public void maleClick(){
        user.setSex("Male");
    }
    
    public void femaleClick(){
        user.setSex("Female");
    }
    
    public void clearClick(){
        user.setAddress(null);
        user.setUsername(null);
        user.setAge(0);
        user.setPassword(null);
        user.setSex(null);
        male.setSelected(false);
        female.setSelected(false);
        usernameReg.setText("");
        passwordReg.setText("");
        ageReg.setText("");
        addressReg.setText("");
    }
    
    public void cancelClick(){
        stage.setScene(loginScene);
    }
    
    public void saveClick(){
        Properties p = new Properties();
        try {
            InputStream input = new FileInputStream("config.ini");
             p.load(input);
        } catch (IOException ex) {
            Logger.getLogger(LoginPageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        user.setUsername(usernameReg.getText());
        if(p.getProperty("username").equals(user.getUsername())){
            label.setText("!!! Username already exists !!!");
        }else{
            user.setPassword(passwordReg.getText());
            user.setAddress(addressReg.getText());
            int a;
            try{
                a = Integer.parseInt(ageReg.getText());
            }catch(NumberFormatException e){
                a = 0;
            }
            user.setAge(a);
               
            if("".equals(user.getUsername()) || "".equals(passwordReg.getText()))
                label.setText("!!! Fill the required fields !!!");
            else{
            user.saveUser();
            stage.setScene(loginScene);
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        
        user.setSex("");
        
    }    
    
}
