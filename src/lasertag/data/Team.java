package lasertag.data;

import java.util.ArrayList;

public class Team {

    private static int uniqueId = 0;
    private int id;
    private String name;
    private ArrayList<Player> playerList;
    private int numberOfPlayers;
    private boolean inChampionship;

    Team(String name) {
        id = ++uniqueId;
        this.name = name;
        numberOfPlayers = 2;
        inChampionship = false;

        playerList = new ArrayList<>(numberOfPlayers);
    }

    void addPlayer(Player player) {
        playerList.add(player);
    }

    public  ArrayList<Player> getPlayers() {
        return playerList;
    }

    public int getId() {
        return id;
    }

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

    public int getNumberOfHitsGiven() {
        int hits  = 0;
        for (Player p : playerList) {
            hits += p.getNumberOfHitsGiven();
        }
        return hits;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void resetScore() {
        for (Player p : playerList) {
            p.resetSore();
        }
    }
}
