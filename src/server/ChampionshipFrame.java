package server;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import data.Team;

public class ChampionshipFrame extends JFrame{

    ArrayList<Team> championshipTeams;

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

    }

}
