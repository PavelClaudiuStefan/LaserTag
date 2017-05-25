package lasertag.server;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

import lasertag.data.Game;
import lasertag.data.Player;
import lasertag.data.Team;
import lasertag.data.Round;

class ChampionshipFrame extends JFrame{

    private Game currentGame = null;
    private ArrayList<Team> championshipTeams;
    private ArrayList<Round> rounds;
    private JTextArea debugTextArea;
    private JPanel bracketPanel;
    private JScrollPane bracketPanelScroll;
    private GameScoreFrame gameScoreFrame;
    private JPanel mainPanel;

    private GridBagConstraints constraints;

    private boolean championshipOver;

    private Team championshipWinner;

    ChampionshipFrame(ArrayList<Team> championshipTeams) {
        super("Championship");
        this.championshipTeams = championshipTeams;

        //Set random matchups between the teams
        long seed = System.nanoTime();
        Collections.shuffle(championshipTeams, new Random(seed));

        setLayout(new GridBagLayout());

        //debugFooter();
        initiateBracket();

        currentGame = rounds.get(0).getGame(0);
        currentGame.setCurrentGame(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(mainPanel, constraints);

        setBracketPanel();

        //NumberOfTeams == 2
        int width = 300;
        int height = 100;
        int numberOfTeams = championshipTeams.size();
        int numberOfRounds = rounds.size();
        if(numberOfTeams > 2 && numberOfTeams <= 32) {
            width = numberOfRounds * 200;
            height = numberOfTeams * 30;
        } else if(numberOfTeams > 32){
            width = 1000;
            height = 900;
        }
        setSize(width, height);

        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.revalidate();
        mainPanel.repaint();

        championshipOver = false;
    }

    private void debugFooter() {
        debugTextArea = new JTextArea();
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        debugTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(debugTextArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.black));

        add(scroll, constraints);
    }

    private void debugInfo(String message) {
        if (debugTextArea != null) {
            try {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String currentTime = sdf.format(cal.getTime());
                message = currentTime + ": " + message + "\n";
                debugTextArea.getDocument().insertString(0, message, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    private void setBracketPanel() {
        bracketPanel = new JPanel();
        bracketPanel.setLayout(new GridBagLayout());
        bracketPanel.setBackground(Color.CYAN);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        bracketPanelScroll = new JScrollPane(bracketPanel);
        bracketPanelScroll.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(bracketPanelScroll, constraints);

        for(int i = 0; i < getNumberOfRounds(); i++) {
            setRoundPanel(i);
        }
    }

    private void setRoundPanel(int roundNumber) {
        JPanel roundPanel = new JPanel();
        roundPanel.setLayout(new GridBagLayout());
        roundPanel.setBackground(Color.CYAN);
        constraints = new GridBagConstraints();
        constraints.gridx = roundNumber;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        roundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bracketPanel.add(roundPanel, constraints);
        addGamesButtons(roundPanel, roundNumber);
    }

    private void addGamesButtons(JPanel roundPanel, int roundNumber) {
        for(int i = 0; i < rounds.get(roundNumber).getNumberOfGames(); i++) {
            Game game = rounds.get(roundNumber).getGame(i);
            JButton button;
            if (game.hasWinner()) {
                button = new JButton(game.getLabelWithWinner());
            } else {
                button = new JButton(game.gameLabel());
            }
            button.setFocusable(false);
            if (game.isCurrentGame()) {
                button.setBackground(Color.green);
            }
            button.addActionListener(e -> {
                if(gameScoreFrame != null) {
                    gameScoreFrame.dispose();
                }
                setGameScoreFrame(game);
            });
            constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = i;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            roundPanel.add(button, constraints);
        }
    }

    private void setGameScoreFrame(Game game) {
        gameScoreFrame = new GameScoreFrame(game);
        gameScoreFrame.showGameScore();
    }

    private void initiateBracket() {
        for(int i = 0; i < this.getNumberOfRounds(); i++) {
            if (i == 0) {
                rounds = new ArrayList<>();
                ArrayList<Game> games = formGamesWithTeams(championshipTeams, championshipTeams.size(), i);
                rounds.add(new Round(games));
            } else {
                ArrayList<Game> games = formGamesWithTeams(rounds.get(i-1).getWinners(), rounds.get(i-1).getNumberOfGames(), i);
                rounds.add(new Round(games));
            }
        }
    }

    private ArrayList<Game> formGamesWithTeams(ArrayList<Team> teams, int numberOfTeams, int roundNumber) {
        ArrayList<Game> games = new ArrayList<>();
        int teamsSize = teams.size();
        for (int j = 0; j < numberOfTeams; j += 2) {
            if (j < teamsSize-1) {
                games.add(new Game(roundNumber, j/2, teams.get(j), teams.get(j+1)));
            } else if (j == teamsSize-1) {
                games.add(new Game(roundNumber, j/2, teams.get(j)));
            } else {
                games.add(new Game(roundNumber, j/2));
            }
        }
        return games;
    }

    private int getNumberOfRounds() {
        int numberOfTeams = championshipTeams.size();
        int numberOfRounds = 0;
        while(numberOfTeams % 2 == 0) {
            numberOfRounds++;
            numberOfTeams /= 2;
        }
        return numberOfRounds;
    }


    //Update championshipFrame, player score, gameScoreFrame
    void update (int source, int target) {
        debugInfo("Player #" + source + " hit Player #" + target);
        // If championship started (currentGameTeams is not null) and both players are alive and in the current game,
        // update players and database
        if (!championshipOver) {
            if(currentGame != null) {
                //p1 - Player in Team 1
                for (Player p1 : currentGame.getTeam(1).getPlayers()) {
                    //p2 - Player in Team 2
                    for (Player p2 : currentGame.getTeam(2).getPlayers()) {
                        //Case 1 : Team 1 player hit a team 2 player
                        if ( (p1.isAlive() && p1.getId() == source) && (p2.isAlive() && p2.getId() == target) && (p1.canHit()) ) {
                            p1.hit();
                            p2.wasHit();
                            int indexP1 = currentGame.getTeam(1).getPlayers().indexOf(p1);
                            int indexP2 = currentGame.getTeam(2).getPlayers().indexOf(p2);
                            currentGame.updateGame(indexP1, indexP2);
                            if (gameScoreFrame != null) {
                                gameScoreFrame.updateGameScore();
                            }

                            //Case 2 : Team 2 player hit a team 1 player
                        } else if ( (p1.isAlive() && p1.getId() == target) && (p2.isAlive() && p2.getId() == source) && (p2.canHit()) ) {
                            p2.hit();
                            p1.wasHit();
                            int indexP2 = currentGame.getTeam(2).getPlayers().indexOf(p2);
                            int indexP1 = currentGame.getTeam(1).getPlayers().indexOf(p1);
                            currentGame.updateGame(indexP1, indexP2);
                            if (gameScoreFrame != null) {
                                gameScoreFrame.updateGameScore();
                            }
                        }

                        //Test if game is over
                        if (currentGame.hasWinner()) {
                            advanceTeam(currentGame.getWinner(), currentGame.getRoundNumber(), currentGame.getRoundOrder());
                            currentGame.setCurrentGame(false);
                            if (currentGame.getRoundNumber() != rounds.size() - 1) {
                                //if current game is not the final championship game
                                currentGame = nextGame(currentGame.getRoundNumber(), currentGame.getRoundOrder());
                                currentGame.setCurrentGame(true);
                            } else {
                                championshipWinner = currentGame.getWinner();
                                championshipOver = true;
                            }

                            mainPanel.remove(bracketPanelScroll);
                            setBracketPanel();
                            mainPanel.revalidate();
                            mainPanel.repaint();

                            if (championshipOver)
                                break;
                        }
                    }
                }
            }
        }
    }

    //Returns the next game in the current round, or the first game in the next round
    private Game nextGame(int roundNumber, int roundOrder) {
        if(roundOrder < rounds.get(roundNumber).getNumberOfGames() - 1) {
            return rounds.get(roundNumber).getGame(roundOrder + 1);
        } else {
            return rounds.get(roundNumber + 1).getGame(0);
        }
    }

    //The winning team is advanced in the next round, or if it was the final game it displays the championship winner
    private void advanceTeam(Team winningTeam, int roundNumber, int roundOrder){
        if(roundNumber < rounds.size()-1) {
            rounds.get(roundNumber + 1).getGame(roundOrder / 2).setTeam(winningTeam, roundOrder % 2);
        } else {
            if (championshipOver) {
                if(gameScoreFrame != null) {
                    gameScoreFrame.dispose();
                }
                System.out.println("\n\nChampionship over!");
                System.out.println(winningTeam.getName() + " is the best team!");

                JOptionPane.showMessageDialog(this,
                        championshipWinner.getName() + " wins the championship!",
                        "Congratulations!",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }

    }
}
