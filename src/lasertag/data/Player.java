package lasertag.data;

import java.util.Date;

public class Player {

    private int id;
    private int teamId;
    private String name;
    private static int lastIdUsed = -1;
    private long lastTimeHit;

    private int numberOfHitsGiven;
    private int numberOfHitsTaken;
    private boolean alive;

    Player(int id, String name,  int teamId) {
        this.id = id;
        lastIdUsed = id;
        this.teamId = teamId;
        this.name = name;
        alive = true;
    }

    public Player(String name, int teamId) {
        this.id = ++lastIdUsed;
        this.teamId = teamId;
        this.name = name;
        alive = true;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfHitsGiven() {
        return numberOfHitsGiven;
    }

    public void hit() {
        numberOfHitsGiven++;
    }

    public int getNumberOfHitsTaken() {
        return numberOfHitsTaken;
    }

    public void wasHit() {
        lastTimeHit = new Date().getTime();
        numberOfHitsTaken++;
        if (numberOfHitsTaken == 3) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    void resetSore() {
        numberOfHitsTaken = 0;
        numberOfHitsGiven = 0;
        alive = true;
    }

    int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public boolean canHit() {
        return (new Date().getTime() - lastTimeHit) > 5000;
    }
}
