package lasertag.main;

import lasertag.server.ServerGUI;

public class StartServer {

    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI("localhost", 1234);
        serverGUI.start();
    }

}
