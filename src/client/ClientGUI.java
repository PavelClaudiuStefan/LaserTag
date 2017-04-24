package client;

import data.InfoMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private Client client;
    private JLabel sourcePlayerLabel;
    private JTextField sourcePlayer;
    private JLabel targetPlayerLabel;
    private JTextField targetPlayer;
    private JButton sendInfoButton;

    //private InfoMessage infoMessage;

    public ClientGUI(String host, int port) {
        super("Client");
        client = new Client(host, port, this);
        client.start();
    }

    public void start() {
        //TEMPORAR

        JPanel panel = new JPanel(new GridLayout(3,2));
        sourcePlayerLabel = new JLabel("First player");
        panel.add(sourcePlayerLabel);
        sourcePlayer = new JTextField("0");
        panel.add(sourcePlayer);

        targetPlayerLabel = new JLabel("Second player");
        panel.add(targetPlayerLabel);
        targetPlayer = new JTextField("1");
        panel.add(targetPlayer);

//        tempButton = new JLabel("Button");
//        panel.add(tempButton);
        sendInfoButton = new JButton("Send info");
        sendInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TEMPORAR
                System.out.println(sourcePlayer.getText() + " " + targetPlayer.getText());
                //client.sendInfoToPlayer(Integer.parseInt(sourcePlayer.getText()), Integer.parseInt(targetPlayer.getText()));
                client.sendInfoToPlayer(0, 1);

            }
        });
        panel.add(sendInfoButton);

        add(panel);


        setSize(200, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //END OF TEMPORAR
    }


}
