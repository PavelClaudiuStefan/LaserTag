package lasertag.server;

import lasertag.data.Team;
import lasertag.misc.Sound;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIButtonListener implements ActionListener  {

    private int option;
    private TeamsFrame teamsFrame;
    private ServerGUI serverGUI;

    static final int NEW_TEAM = 1;
    static final int ADD_TEAM = 2;
    static final int REMOVE_TEAM = 3;
    static final int START_CHAMPIONSHIP = 4;

    GUIButtonListener(int option, TeamsFrame teamsFrame) {
        this.option = option;
        this.teamsFrame = teamsFrame;
    }

    GUIButtonListener(int option, TeamsFrame teamsFrame, ServerGUI serverGUI) {
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
        teamsFrame.startNewTeamsFrame();
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
            //Sound.playSound("xpStart.wav");
        } else {
            Sound.playSound("xpError.wav");

            JOptionPane.showMessageDialog(teamsFrame,
                    "The number of teams must be a power of 2, greater than 1 (Examples: 2, 4, 8, 16, 32...)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validNumberOfTeams(int x) {
        return ((x & (x - 1)) == 0 && x != 0 && x != 1) || x == 2;
    }

}
