package lasertag.server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.ArrayList;

import lasertag.data.Team;

public class ChampionshipFrame extends JFrame{

    ArrayList<Team> championshipTeams;
    JTextArea debugTextArea;

    ChampionshipFrame(ArrayList<Team> championshipTeams) {
        super("Championship");
        this.championshipTeams = championshipTeams;

        setLayout(new GridBagLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void start() {
        setDebugHeader();
    }

    private void setDebugHeader() {
        debugTextArea = new JTextArea();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        debugTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(debugTextArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));
        add(scroll, constraints);
    }

    void updateScore(String message) {
        try {
            debugTextArea.getDocument().insertString(0, message, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        //TODO
        //Update Players score and database
    }

}
