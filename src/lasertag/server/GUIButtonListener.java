package lasertag.server;

import lasertag.data.Team;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIButtonListener implements ActionListener  {

    //TODO
    ChampionshipFrame championshipFrame;

    private int option;
    private TeamsFrame teamsFrame;

    public static final int NEW_TEAM = 1;
    public static final int ADD_TEAM = 2;
    public static final int REMOVE_TEAM = 3;
    public static final int START_CHAMPIONSHIP = 4;

    public GUIButtonListener(int option, TeamsFrame teamsFrame) {
        this.option = option;
        this.teamsFrame = teamsFrame;
    }

    public GUIButtonListener(int option, TeamsFrame teamsFrame, ChampionshipFrame cFrame) {
        this.option = option;
        this.teamsFrame = teamsFrame;
        this.championshipFrame = cFrame;
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
    }

    private void startChampionship() {
        System.out.println("GOODBYE TEAMS FRAME");

        ArrayList<Team> championshipTeams = new ArrayList<>(101);
        for (Team team : teamsFrame.teamsList) {
            if (team.isInChampionship()) {
                championshipTeams.add(team);
            }
        }
        championshipFrame = new ChampionshipFrame(championshipTeams);
        championshipFrame.start();

        teamsFrame.dispose();
    }
}
