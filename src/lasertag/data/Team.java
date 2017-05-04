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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
