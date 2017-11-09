package client.controller;

import client.application.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.*;

import javafx.scene.Scene;
import client.application.LoginEvent;

public class LoginController {

    @FXML
    private TextField fieldUsername;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Label labelError;
    private Client client;
    private LoginEvent cel = new LoginEvent() {
        @Override
        public void invalidLogin() {
            labelError.setText("Invalid username or password!");
            client.disconnect();
        }

        @Override
        public void successfulLogin(ActionEvent event) {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/view/ChatView.fxml"));

            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatController controller = fxmlLoader.getController();
            controller.init(client);

            Stage stage = new Stage();
            Scene scene = new Scene(parent);

            stage.setScene(scene);
            stage.setTitle("Chat");
            stage.setMinWidth(330);
            stage.setMinHeight(400);
            stage.show();
        }

        @Override
        public void newAccountCreated() {
            labelError.setTextFill(Paint.valueOf("#00d815"));
            labelError.setText("New account has been created!");
        }

        @Override
        public void usernameUsed() {
            labelError.setTextFill(Paint.valueOf("#da0000"));
            labelError.setText("This username is already used!");
        }

        @Override
        public void cannotConnect() {
            labelError.setText("Cannot connect to server!");
        }

    };

    @FXML
    public void btnLogInAction(ActionEvent event) {
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        client = new Client(username, password);
        client.setEventLogin(cel);
        if (client.connect("localhost", 5000)) {
            client.login(event);
        }
    }

    @FXML
    public void btnSaveAction() {
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();

        client = new Client(username, password);
        client.setEventLogin(cel);
        if (client.connect("localhost", 5000)) {
            client.register();
            cel.newAccountCreated();
            client.disconnect();
            labelError.setText("");
        }

        fieldUsername.clear();
        fieldPassword.clear();
    }

    @FXML
    public void btnClearAction() {
        fieldUsername.clear();
        fieldPassword.clear();
    }
}
