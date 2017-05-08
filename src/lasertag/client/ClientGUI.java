package lasertag.client;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private Client client;

    public ClientGUI(String host, int port) {
        super("Client");
        client = new Client(host, port);
        client.start();

        //
        simulateChampionship();
    }

    public synchronized void start() {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel sourcePlayerLabel = new JLabel("Source:");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(sourcePlayerLabel, constraints);

        JTextField sourcePlayer = new JTextField("0");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(sourcePlayer, constraints);


        JLabel targetPlayerLabel = new JLabel("Target:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(targetPlayerLabel, constraints);

        JTextField targetPlayer = new JTextField("1");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(targetPlayer, constraints);


        JButton sendInfoButton = new JButton("Send info");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        sendInfoButton.addActionListener(e -> client.sendInfoToPlayer(Integer.parseInt(sourcePlayer.getText()), Integer.parseInt(targetPlayer.getText())));
        panel.add(sendInfoButton, constraints);

        add(panel);

        setSize(250, 200);
        //setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void simulateChampionship() {
        for(int i = 0; i < 30; i+=2) {
            for (int j = 0; j < 3; j++) {
                client.sendInfoToPlayer(i, i+2);
                System.out.println(i + " " + (i + 2));
                client.sendInfoToPlayer(i, i+3);
                System.out.println(i + " " + (i + 3));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}
