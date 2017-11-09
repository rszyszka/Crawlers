package client.application;

public interface ChatEvent {

    void messageReceived(String msg);

    void disconnectedFromTheServer();
}
