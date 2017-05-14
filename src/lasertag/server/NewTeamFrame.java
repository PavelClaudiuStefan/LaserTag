package lasertag.server;

import lasertag.data.Player;
import lasertag.data.Team;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class NewTeamFrame extends JFrame {

    //Maximum number of players in a team
    private int numberOfPlayers;

    private ArrayList<JTextField> playerNames;
    private JTextField teamName;
    private TeamsFrame teamsFrame;

    private GridBagConstraints constraints;

    NewTeamFrame(TeamsFrame teamsFrame) {
        super("New Team");

        setLayout(new GridBagLayout());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.teamsFrame = teamsFrame;

        numberOfPlayers = 2;
    }

    public void start() {

        playerNames = new ArrayList<>();

        //Panel with player names, and submit button
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.CYAN);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(panel, constraints);

        for(int i = 0; i < numberOfPlayers; i++){
            //Player name label
            JLabel playerNameLabel = new JLabel("Player #" + i + " name:", SwingConstants.CENTER);
            constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.weightx = 0.2;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            panel.add(playerNameLabel, constraints);

            //Player name JTextField
            JTextField playerName = new JTextField();
            playerNames.add(playerName);
            constraints = new GridBagConstraints();
            constraints.gridx = 1;
            constraints.gridy = i;
            constraints.weightx = 0.8;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            panel.add(playerName, constraints);
        }

        //Team name label
        JLabel teamNameLabel = new JLabel("Team name:", SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = numberOfPlayers;
        constraints.weightx = 0.2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(teamNameLabel, constraints);

        //Team name JTextField
        teamName = new JTextField();
        playerNames.add(teamName);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = numberOfPlayers;
        constraints.weightx = 0.8;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(teamName, constraints);

        //Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.dispose());
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = numberOfPlayers + 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(cancelButton, constraints);

        //Submit button
        JButton createTeamButton = new JButton("Create team");
        /*
        createTeamButton.addActionListener(e -> {
            Team team = new Team(teamName.getText());
            for (int i = 0; i < numberOfPlayers; i++){
                Player player = new Player(playerNames.get(i).getText());
                team.addPlayer(player);
            }
            teamsFrame.teamsList.add(team);
            teamsFrame.update();
            //TODO - Update database
            this.dispose();
        });
        */
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = numberOfPlayers + 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(createTeamButton, constraints);

        revalidate();
        repaint();
    }

}
