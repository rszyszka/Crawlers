package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {

    private final List<ServerThread> threads = new ArrayList<>();
    private final Server server = this;
    private boolean looped = false;
    private ServerSocket finalServerSocket;

    public static void main(String args[]) throws IOException {
        Server server = new Server();
        server.start();
        System.in.read();
        server.stop();
    }

    public void start() {
        ServerSocket serverSocket = null;
        final Socket[] socket = {null};

        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        finalServerSocket = serverSocket;
        new Thread("Nowi_klienci") {
            public void run() {
                if (!looped) {
                    System.out.println("Listening....");
                    looped = true;
                    while (looped) {
                        try {
                            socket[0] = finalServerSocket.accept();
                        } catch (IOException e) {
                            break;
                        }

                        ServerThread et = new ServerThread(socket[0], server);
                        et.setDaemon(true);
                        et.start();

                        threads.add(et);
                    }
                }
            }
        }.start();
    }

    public void stop() {
        new Thread("Close") {
            public void run() {
                looped = false;

                for (ServerThread el : threads) {
                    el.close();
                }

                try {
                    finalServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void sayAll(String user, String msg) throws IOException {
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        for (ServerThread el : threads) {
            el.say("<html><font size=\"3\">[" + dateformat.format(new Date()) + "] " + user + ":  " + msg + "</font></html>");
        }
    }

    public void removeThread(ServerThread et) {
        threads.remove(et);
    }

}
