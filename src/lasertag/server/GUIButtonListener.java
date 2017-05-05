package lasertag.server;

import jdk.nashorn.internal.scripts.JD;
import lasertag.data.Team;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class GUIButtonListener implements ActionListener  {

    //TODO
    ChampionshipFrame championshipFrame;

    private int option;
    private TeamsFrame teamsFrame;
    private ServerGUI serverGUI;

    public static final int NEW_TEAM = 1;
    public static final int ADD_TEAM = 2;
    public static final int REMOVE_TEAM = 3;
    public static final int START_CHAMPIONSHIP = 4;

    public GUIButtonListener(int option, TeamsFrame teamsFrame) {
        this.option = option;
        this.teamsFrame = teamsFrame;
    }

    public GUIButtonListener(int option, TeamsFrame teamsFrame, ServerGUI serverGUI) {
        this.option = option;
        this.teamsFrame = teamsFrame;
        this.serverGUI = serverGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (option) {
            case NEW_TEAM:
                newTeam();
                break;
            case ADD_TEAM:
                addTeam();
                break;
            case REMOVE_TEAM:
                removeTeam();
                break;
            case START_CHAMPIONSHIP:
                startChampionship();
        }
    }

    private void newTeam() {

    }

    private void addTeam() {
        for (int i = 0; i < teamsFrame.teamsList.size(); i++) {
            if (!teamsFrame.teamsList.get(i).isInChampionship() && teamsFrame.teamCheckBoxesList.get(i).isSelected()) {
                teamsFrame.teamsList.get(i).setInChampionship(true);
                teamsFrame.teamCheckBoxesList.get(i).setSelected(false);
                teamsFrame.moveToChampionship(teamsFrame.teamCheckBoxesList.get(i));
            }
        }
        //teamsFrame.revalidate();
        teamsFrame.repaint();
    }

    private void removeTeam() {
        for (int i = 0; i < teamsFrame.teamsList.size(); i++) {
            if (teamsFrame.teamsList.get(i).isInChampionship() && teamsFrame.teamCheckBoxesList.get(i).isSelected()) {
                System.out.println(teamsFrame.teamCheckBoxesList.get(i).getText());
                teamsFrame.teamsList.get(i).setInChampionship(false);
                teamsFrame.teamCheckBoxesList.get(i).setSelected(false);
                teamsFrame.moveFromChampionship(teamsFrame.teamCheckBoxesList.get(i));
            }
        }
        //teamsFrame.revalidate();
        teamsFrame.repaint();
    }

    private void startChampionship() {
        ArrayList<Team> championshipTeams = new ArrayList<>(101);
        for (Team team : teamsFrame.teamsList) {
            if (team.isInChampionship()) {
                championshipTeams.add(team);
            }
        }
        if (validNumberOfTeams(championshipTeams.size())) {
            serverGUI.startChampionship(championshipTeams);
            teamsFrame.dispose();
        } else {
            playSound("xpError.wav");

            JOptionPane.showMessageDialog(teamsFrame,
                    "The number of teams must be a power of 2, greater than 1 (Examples: 2, 4, 8, 16, 32...)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validNumberOfTeams(int x) {
        boolean isValid = (x & (x - 1)) == 0 && x != 0 && x != 1;
        System.out.println("x = " + x + " " + isValid);
        return isValid;
    }

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            GUIButtonListener.class.getResourceAsStream("/lasertag/sounds/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
