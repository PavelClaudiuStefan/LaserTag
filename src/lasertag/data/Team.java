package lasertag.data;

import java.util.ArrayList;

public class Team {

    private static int lastIdUsed = -1;
    private int id;
    private String name;
    private ArrayList<Player> playerList;
    private int numberOfPlayers;
    private boolean inChampionship;

    public Team(int id, String name) {
        this.id = id;
        lastIdUsed = id;
        this.name = name;
        numberOfPlayers = 2;
        inChampionship = false;

        playerList = new ArrayList<>(numberOfPlayers);
    }

    public Team(String name) {
        this.id = ++lastIdUsed;
        this.name = name;
        numberOfPlayers = 2;
        inChampionship = false;

        playerList = new ArrayList<>(numberOfPlayers);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public  ArrayList<Player> getPlayers() {
        return playerList;
    }

    /*public int getId() {
        return id;
    }*/

    public String getName() {
        return name;
    }

    boolean isLosingTeam() {
        boolean teamLost = true;
        for(Player p : this.getPlayers()) {
            if (p.isAlive()) {
                teamLost = false;
            }
        }
        return teamLost;
    }

    public boolean isInChampionship() {
        return inChampionship;
    }

    public void setInChampionship(boolean inChampionship) {
        this.inChampionship = inChampionship;
    }

    @Override
    public String toString() {
        return name + " #" + id;
    }

    int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    void resetScore() {
        for (Player p : playerList) {
            p.resetSore();
        }
    }

    public int getId() {
        return id;
    }

}
