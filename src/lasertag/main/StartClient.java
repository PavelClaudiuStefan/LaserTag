package lasertag.main;

import lasertag.client.ClientGUI;

public class StartClient {

    public static void main(String[] args) {
        ClientGUI clientGUI = new ClientGUI("localhost", 1234);
        clientGUI.start();
    }

}
