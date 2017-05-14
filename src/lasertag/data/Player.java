package lasertag.data;

public class Player {

    private int id;
    //teamId == -1 : The player is not in a team
    private int teamId;
    private String name;
    private static int lastIdUsed;

    private int numberOfHitsGiven;
    private int numberOfHitsTaken;
    private boolean alive;

    Player(int id, String name) {
        this.id = id;
        lastIdUsed = id;
        teamId = -1;
        this.name = name;
        alive = true;
    }

    public Player(String name) {
        this.id = lastIdUsed;
        lastIdUsed++;
        teamId = -1;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }
}
