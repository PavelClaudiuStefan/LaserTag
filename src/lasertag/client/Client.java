package lasertag.client;

import lasertag.data.InfoMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client {

    private String host;
    private int port;
    private ObjectOutputStream output;

    Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    void start() {
        try {
            Socket socket = new Socket(host, port);
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendInfoToPlayer(int source, int target) {
        try {
            InfoMessage infoMessage = new InfoMessage(source, target);
            output.writeObject(infoMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void end() {
        try {
            InfoMessage infoMessage = new InfoMessage("end");
            output.writeObject(infoMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}