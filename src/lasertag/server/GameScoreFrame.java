package lasertag.server;

import lasertag.data.Game;
import lasertag.data.Player;
import lasertag.data.Team;

import javax.swing.*;
import java.awt.*;

public class GameScoreFrame extends JFrame{

    JPanel gameScorePanel;
    GridBagConstraints constraints;
    Game game;

    public GameScoreFrame(Game game) {

        super("Game score");

        this.game = game;

        setLayout(new GridBagLayout());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.CYAN);

    }

    public void showGameScore() {
        gameScorePanel = new JPanel();
        gameScorePanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(gameScorePanel, constraints);

        String label;

        JPanel leftPanel = new JPanel();    //Team 1
        leftPanel.setBackground(Color.CYAN);
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        gameScorePanel.add(leftPanel, constraints);
        if(game.getDeterminedTeams () == 0) {
            JLabel leftTeamLabel = defaultLabel();
            constraints = new GridBagConstraints();
            constraints.gridy = 0;
            leftPanel.add(leftTeamLabel, constraints);
        } else {
            Team team = game.getTeam(0);
            label = team.getName() + ": " + team.getNumberOfHitsGiven();

            //Team name
            JLabel leftTeamLabel = new JLabel(label);
            leftTeamLabel.setFont(new Font("Serif", Font.BOLD, 18));
            constraints = new GridBagConstraints();
            constraints.gridy = 0;
            leftPanel.add(leftTeamLabel, constraints);

            //Score and name init
            JLabel playersScoreAndName1 = new JLabel("Score   /   Player");
            constraints = new GridBagConstraints();
            constraints.gridy = 1;
            leftPanel.add(playersScoreAndName1, constraints);

            //Score and names
            setPlayerScores(leftPanel, team);
        }



        JPanel middlePanel = new JPanel();  //"VS"
        middlePanel.setBackground(Color.CYAN);
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        gameScorePanel.add(middlePanel, constraints);
        JLabel vsLabel = new JLabel("VS");
        middlePanel.add(vsLabel);


        JPanel rightPanel = new JPanel();   //Team 2
        rightPanel.setBackground(Color.CYAN);
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        gameScorePanel.add(rightPanel, constraints);
        if(game.getDeterminedTeams () != 2) {
            JLabel rightTeamLabel = defaultLabel();
            constraints = new GridBagConstraints();
            constraints.gridy = 0;
            rightPanel.add(rightTeamLabel, constraints);
        } else {
            Team team = game.getTeam(1);
            label = team.getName() + ": " + team.getNumberOfHitsGiven();

            //Team name
            JLabel rightTeamLabel = new JLabel(label);
            rightTeamLabel.setFont(new Font("Serif", Font.BOLD, 18));
            constraints = new GridBagConstraints();
            constraints.gridy = 0;
            rightPanel.add(rightTeamLabel, constraints);

            //Score and name init
            JLabel playersScoreAndName2 = new JLabel("Score/Player");
            constraints = new GridBagConstraints();
            constraints.gridy = 1;
            rightPanel.add(playersScoreAndName2, constraints);
            //Score and names
            setPlayerScores(rightPanel, team);
        }

    }

    private JLabel defaultLabel() {
        JLabel defaultTeamLabel = new JLabel("TBD");
        defaultTeamLabel.setFont(new Font("Serif", Font.BOLD, 18));
        return defaultTeamLabel;
    }

    private void setPlayerScores(JPanel panel, Team team) {
        int y = 1;
        for (Player p : team.getPlayers()) {
            JLabel scoreName = new JLabel(p.getNumberOfHitsGiven() + "/" + p.getNumberOfHitsTaken() + " - " + p.getName());
            constraints = new GridBagConstraints();
            constraints.gridy = ++y;
            panel.add(scoreName, constraints);
        }
    }

    public void updateGameScore() {
        remove(gameScorePanel);
        showGameScore();
        revalidate();
        repaint();
    }

}