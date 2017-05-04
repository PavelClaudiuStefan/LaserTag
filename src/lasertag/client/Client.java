package lasertag.client;

import lasertag.data.InfoMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String host;
    private int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ClientGUI clientGUI;
    private InfoMessage infoMessage;

    public Client(String host, int port, ClientGUI clientGUI) {
        this.host = host;
        this.port = port;
        this.clientGUI = clientGUI;
    }

    public void start() {
        try {
            socket = new Socket(host, port);
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendInfoToPlayer(int source, int target) {

        try {
            infoMessage = new InfoMessage(source, target);
            output.writeObject(infoMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}