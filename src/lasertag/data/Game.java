package lasertag.data;

public class Game {

    private Team team1, team2;
    private String gameLabel;
    private boolean currentGame;
    private int determinedTeams;
    private int roundNumber;
    private int roundOrder;

    public Game(int roundNumber, int roundOrder) {
        currentGame = false;
        gameLabel = "TBD vs TBD";   //To Be Determined
        determinedTeams = 0;
        this.roundNumber = roundNumber;
        this.roundOrder = roundOrder;
    }

    public Game(int roundNumber, int roundOrder, Team team1) {
        this.team1 = team1;
        currentGame = false;
        gameLabel = team1.getName() + " vs TBD";
        determinedTeams = 1;
        this.roundNumber = roundNumber;
        this.roundOrder = roundOrder;
    }

    public Game(int roundNumber, int roundOrder, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        currentGame = false;
        gameLabel = team1.getName() + " vs " + team2.getName();
        determinedTeams = 2;
        this.roundNumber = roundNumber;
        this.roundOrder = roundOrder;
    }

    public void setTeam(Team team, int number) {
        if(number == 0) {
            team1 = team;
        } else {
            team2 = team;
        }
    }

    public Team getTeam(int teamNumber){
        if (teamNumber == 0) {
            return team1;
        } else {
            return team2;
        }

    }

    public boolean hasWinner() {
        if(team1 == null || team2 == null)
            return false;
        if(team1.isLosingTeam() || team2.isLosingTeam())
            return true;
        return false;
    }

    public Team getWinner() {
        if (team1.isLosingTeam()) {
            return team2;
        } else {
            return team1;
        }
    }

    public String gameLabel() {
        return gameLabel;
    }

    public void setCurrentGame(boolean value) {
        currentGame = value;
    }

    public boolean isCurrentGame() {
        return currentGame;
    }

    public int getDeterminedTeams() {
        return determinedTeams;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getRoundOrder() {
        return roundOrder;
    }
}
