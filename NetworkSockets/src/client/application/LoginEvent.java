package client.application;

import javafx.event.ActionEvent;

public interface LoginEvent {

    void invalidLogin();

    void successfulLogin(ActionEvent event);

    void newAccountCreated();

    void usernameUsed();

    void cannotConnect();
}
