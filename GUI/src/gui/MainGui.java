/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import crawler.Student;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Szysz
 */
public class MainGui extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        AnchorPane tabPane = CustomTabPane.getInstance();
        
        CustomMenuBar menuBar = new CustomMenuBar();
        menuBar.getMenus().add(menuBar.prog);
        menuBar.getMenus().add(menuBar.about1);

        VBox mainWindow = new VBox();
        VBox.setVgrow(tabPane, Priority.ALWAYS);
        mainWindow.getChildren().addAll(menuBar,tabPane);
        Scene scene = new Scene(mainWindow, 500, 450);
        
        primaryStage.setScene(scene);
        primaryStage.show();
   
    }
    
    public void addStudent(Student s){
        CustomTabPane tabPane = CustomTabPane.getInstance();
        tabPane.addStudent(s);
    }
    
    public void removeStudent(Student s){
        CustomTabPane tabPane = CustomTabPane.getInstance();
        tabPane.removeStudent(s);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
      
        new Thread(new CrawRunnable()).start();
        launch(args);
        
    }
    
}
