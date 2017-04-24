package server;

import javax.swing.*;
import java.awt.*;

public class ServerGUI extends JFrame{
    private static final long serialVersionUID = 1L;

    private String host;
    private int port;
    private Server server;
    //TEMPORAR
    private JTextArea playerActivity;

    public ServerGUI(String host, int port) {
        super("Laser Tag Championship");
        this.host = host;
        this.port = port;
        server = new Server(host, port, this);

    }

    public void start() {
        //TEMPORAR

        JPanel center = new JPanel(new GridLayout(1,1));
        playerActivity = new JTextArea(80,80);
        playerActivity.setEditable(false);
        playerActivity.append("Player activity:\n");
        center.add(new JScrollPane(playerActivity));
        add(center);

        setSize(400, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //END OF TEMPORAR

        //Start as thread
        new ServerRunning().start();

    }

    void updateScore(String message) {
        //TEMPORAR
        playerActivity.append(message);
    }

    class ServerRunning extends Thread {
        public void run() {
            // should execute until if fails
            server.start();
            // the server failed
            server = null;
        }
    }

}
