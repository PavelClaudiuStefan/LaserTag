package data;

public class Player {

    //More atributes:
    // - Team
    // - inActiveGame
    private int id;
    private String name;

    private int numberOfHitsGiven;
    private int numberOfHitsTaken;
    private boolean inActiveGame;

    Player(int id, String name) {
        this.id = id;
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
}
