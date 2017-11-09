/*
 * Copyright (C) 2017 Szysz
 */
package gui;

import crud.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Szysz
 */
public class MainGui extends Application {

    private LoginPageController loginPageController;
    private RegisterPageController registerPageController;
    private MainWindowController mainWindowController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader mainWindowLoader = new FXMLLoader(this.getClass().getResource("MainWindow.fxml"));
        Parent mainWindowNode = mainWindowLoader.load();
        mainWindowController = mainWindowLoader.getController();
        Scene mainWindowScene = new Scene(mainWindowNode);

        FXMLLoader loginLoader = new FXMLLoader(this.getClass().getResource("LoginPage.fxml"));
        Parent loginNode = loginLoader.load();
        loginPageController = loginLoader.getController();
        loginPageController.setStage(primaryStage);
        Scene loginScene = new Scene(loginNode);

        FXMLLoader registerLoader = new FXMLLoader(this.getClass().getResource("RegisterPage.fxml"));
        Parent registerNode = registerLoader.load();
        registerPageController = registerLoader.getController();
        registerPageController.setLoginScene(loginScene);
        registerPageController.setStage(primaryStage);
        Scene registerScene = new Scene(registerNode);

        loginPageController.setRegisterScene(registerScene);
        loginPageController.setMainWindowScene(mainWindowScene);

        mainWindowController.setLoginScene(loginScene);
        mainWindowController.setStage(primaryStage);
        loginPageController.setMainWindowController(mainWindowController);
        registerPageController.setMainWindowController(mainWindowController);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        HibernateUtil.getSessionFactory().close();
    }
}
