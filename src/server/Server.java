package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import data.InfoMessage;

public class Server {

    private static int uniqueId;
    private ArrayList<ClientThread> clientList;

    private String host;
    private int port;
    private ServerGUI serverGUI;
    boolean keepGoing;

    Socket socket;

    public Server(String host, int port, ServerGUI serverGUI) {
        clientList = new ArrayList<>();
        this.host = host;
        this.port = port;
        this.serverGUI = serverGUI;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            keepGoing = true;
            while(keepGoing) {
                //TEMPORAR
                System.out.println("Server waiting for Clients on port " + port + ".");

                socket = serverSocket.accept();
                if(!keepGoing) {
                    break;
                }
                ClientThread clientThread = new ClientThread(socket);
                clientList.add(clientThread);
                clientThread.start();
            }
            //keepGoing == false
            try {
                serverSocket.close();
                for (int i = 0; i < clientList.size(); i++) {
                    ClientThread tc = clientList.get(i);

                    try {
                        tc.input.close();
                        tc.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                System.out.println("Exception closing the server and clients: " + e);
            }

        } catch (IOException e) {
            e.printStackTrace();
            //TEMPORAR
            System.out.println("Exception on new ServerSocket: " + e + "\n");
        }

    }

    //Maybe put a JButton in the server gui
    //A way for gui to stop server
    protected void stop() {
        keepGoing = false;
    }

    class ClientThread extends Thread {

        Socket socket;
        ObjectInputStream input;
        InfoMessage infoMessage;
        int id;

        ClientThread(Socket socket) {

            id = ++uniqueId;
            this.socket = socket;

            //TEMPORAR
            //trying to create the input stream
            try {
                input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New client connected!");
        }

        public void run() {
            boolean keepLooping = true;
            while(keepLooping) {
                try {
                    infoMessage = new InfoMessage();
                    infoMessage = (InfoMessage) input.readObject();
                    System.out.println("Debug: " + infoMessage.getSourcePlayer() + infoMessage.getTargetPlayer());
                } catch (IOException e) {
                    e.printStackTrace();
                    //TEMPORAR
                    System.out.println("Exception reading Streams: " + e);
                    keepLooping = false;
                } catch (ClassNotFoundException e) {
                    keepLooping = false;
                }
                //TEMPORAR
                //Do something with infoMessage
                updatePlayer();
            }
            close();
        }

        public void close() {
            // try to close the connection
            try {
                if(input != null) input.close();
            }
            catch(Exception e) {};
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {}
        }

        public void updatePlayer(){
            //Update an object from the Player class
            //If Player is in the active game -> update score
            //serverGUI.updateScore("Player #" + infoMessage.getSourcePlayer() + " shot Player #" + infoMessage.getTargetPlayer() + " in the face!\n");
        }

    }

}














