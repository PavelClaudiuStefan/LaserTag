package data;

public class Player {

    private int id;
    //teamId == -1 : The player is not in a team
    private int teamId;
    private String name;

    private int numberOfHitsGiven;
    private int numberOfHitsTaken;
    private boolean inActiveGame;

    Player(int id, String name) {
        this.id = id;
        teamId = -1;
        this.name = name;
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
            inActiveGame = false;
        }
    }

    public void playerNewGame() {
        numberOfHitsTaken = 0;
        numberOfHitsGiven = 0;
        inActiveGame = true;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
