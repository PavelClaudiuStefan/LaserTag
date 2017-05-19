package lasertag.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import lasertag.data.InfoMessage;

class Server {

    private static int uniqueId;
    private ArrayList<ClientThread> clientList;

    private int port;
    private ServerGUI serverGUI;
    private boolean keepGoing;


    Server(int port, ServerGUI serverGUI) {
        clientList = new ArrayList<>();
        this.port = port;
        this.serverGUI = serverGUI;
    }

    void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            keepGoing = true;
            while(keepGoing) {
                //TEMPORAR
                System.out.println("Server waiting for Clients on port " + port + ".");

                Socket socket = serverSocket.accept();
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
                for (ClientThread tc : clientList) {
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
    /*protected void stop() {
        keepGoing = false;
    }*/

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
                } catch (IOException e) {
                    e.printStackTrace();
                    //TEMPORAR
                    System.out.println("Exception reading Streams: " + e);
                    keepLooping = false;
                } catch (ClassNotFoundException e) {
                    keepLooping = false;
                }
                //TODO - TEMPORAR
                //Do something with infoMessage
                sendInfo();
            }
            close();
        }

        void close() {
            // try to close the connection
            try {
                if(input != null) input.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            try {
                if(socket != null) socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        void sendInfo(){
            serverGUI.sendInfoToChampionshipFrame(infoMessage.getSourcePlayer(), infoMessage.getTargetPlayer());
            //TODO : Update the database
        }

    }

}














