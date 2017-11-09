/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import crawler.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Szysz
 */
public class MainGui extends Application implements Serializable {
    
    private static VBoxFXMLController vBoxFXMLController;
    private LoginPageFXMLController loginPageFXMLController;
    private RegisterPageFXMLController registerPageFXMLController;
    private final List<User> users = new ArrayList<>();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader mainLoader=new FXMLLoader(this.getClass().getResource("VBoxFXML.fxml"));
	Parent mainNode=mainLoader.load();
	vBoxFXMLController = mainLoader.getController();
	Scene mainWindowScene=new Scene(mainNode);
        
        FXMLLoader loginLoader = new FXMLLoader(this.getClass().getResource("LoginPageFXML.fxml"));
        Parent loginNode = loginLoader.load();
        loginPageFXMLController = loginLoader.getController();
        loginPageFXMLController.setStage(stage);
        loginPageFXMLController.setMainWindowScene(mainWindowScene);
        loginPageFXMLController.setUsers(users);
        Scene loginScene = new Scene(loginNode);
        
        FXMLLoader registerLoader = new FXMLLoader(this.getClass().getResource("RegisterPageFXML.fxml"));
        Parent registerNode = registerLoader.load();
        registerPageFXMLController = registerLoader.getController();
        registerPageFXMLController.setStage(stage);
        registerPageFXMLController.setLoginScene(loginScene);
        registerPageFXMLController.setUsers(users);
        Scene registerScene = new Scene(registerNode);
        loginPageFXMLController.setRegisterScene(registerScene);
       
        stage.setScene(loginScene);
        stage.show();
    }
    
    public static synchronized void addStudent(Student student){
        vBoxFXMLController.addStudent(student);
	}
	
    public static synchronized void removeStudent(Student student){
	vBoxFXMLController.removeStudent(student);
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        MainGui gui = new MainGui();
        new Thread(new CrawlerRunnable(gui)).start();
        launch(gui.getClass());
    }
    
}
