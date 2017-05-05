package lasertag.server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.ArrayList;

import lasertag.data.Team;

public class ChampionshipFrame extends JFrame{

    ArrayList<Team> championshipTeams;
    JTextArea debugTextArea;

    GridBagConstraints constraints;

    ChampionshipFrame(ArrayList<Team> championshipTeams) {
        super("Championship");
        this.championshipTeams = championshipTeams;

        setLayout(new GridBagLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setDebugHeader();


        revalidate();
        repaint();
    }

    private void setDebugHeader() {
        debugTextArea = new JTextArea();
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        debugTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(debugTextArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));
        add(scroll, constraints);
        for(Team team : championshipTeams) {
            debugTextArea.append(team.getName() + "\n");
        }
    }

    void debugInfo(int source, int target) {
        try {
            String infoMessage = "Player with id #" + target + " was hit by player with id#" + source + ";\n";
            debugTextArea.getDocument().insertString(1, infoMessage, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    void updateGameScore(int source, int target) {
        //TODO
        System.out.println("Not made yet : " + source + " hit " + target);
    }

}
