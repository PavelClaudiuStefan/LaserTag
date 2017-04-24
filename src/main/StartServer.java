package main;

import server.ServerGUI;

public class StartServer {

    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI("localhost", 1234);
        serverGUI.start();
    }

}
