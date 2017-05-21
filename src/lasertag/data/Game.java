package lasertag.data;

public class Game {

    private Team team1, team2;
    private String gameLabel;
    private String labelWithWinner;
    private boolean currentGame;
    private int determinedTeams;
    private int roundNumber;
    private int roundOrder;
    private int numberOfPlayers;

    //Team 1
    private int[] teamPlayerHitsGiven1;
    private int[] teamPlayerHitsTaken1;

    //Team 2
    private int[] teamPlayerHitsGiven2;
    private int[] teamPlayerHitsTaken2;

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
        numberOfPlayers = team1.getNumberOfPlayers();
        teamPlayerHitsGiven1 = new int[numberOfPlayers];
        teamPlayerHitsTaken1 = new int[numberOfPlayers];
        initiate(teamPlayerHitsGiven1);
        initiate(teamPlayerHitsTaken1);
    }

    public Game(int roundNumber, int roundOrder, Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        currentGame = false;
        gameLabel = team1.getName() + " vs " + team2.getName();
        determinedTeams = 2;
        this.roundNumber = roundNumber;
        this.roundOrder = roundOrder;
        numberOfPlayers = team1.getNumberOfPlayers();
        teamPlayerHitsGiven1 = new int[numberOfPlayers];
        teamPlayerHitsTaken1 = new int[numberOfPlayers];
        initiate(teamPlayerHitsGiven1);
        initiate(teamPlayerHitsTaken1);
        teamPlayerHitsGiven2 = new int[numberOfPlayers];
        teamPlayerHitsTaken2 = new int[numberOfPlayers];
        initiate(teamPlayerHitsGiven2);
        initiate(teamPlayerHitsTaken2);
    }

    public void setTeam(Team team, int number) {
        team.resetScore();
        if(number == 0) {
            team1 = team;
            numberOfPlayers = team1.getNumberOfPlayers();
            teamPlayerHitsGiven1 = new int[numberOfPlayers];
            teamPlayerHitsTaken1 = new int[numberOfPlayers];
            initiate(teamPlayerHitsGiven1);
            initiate(teamPlayerHitsTaken1);
            gameLabel = team1.getName() + " vs TBD";
        } else {
            team2 = team;
            gameLabel = team1.getName() + " vs " + team2.getName();
            teamPlayerHitsGiven2 = new int[numberOfPlayers];
            teamPlayerHitsTaken2 = new int[numberOfPlayers];
            initiate(teamPlayerHitsGiven2);
            initiate(teamPlayerHitsTaken2);
        }

        determinedTeams++;
    }

    public Team getTeam(int teamNumber){
        Team team = null;
            if (teamNumber == 1) {
                team = team1;
            } else if (teamNumber == 2){
                team = team2;
            }
        return team;
    }

    public boolean hasWinner() {
        return !(team1 == null || team2 == null) && (team1.isLosingTeam() || team2.isLosingTeam());
    }


    public Team getWinner() {
        Team winningTeam;
        if (team1.isLosingTeam()) {
            winningTeam =  team2;
            labelWithWinner = "<html>" + team1.getName() + " vs " + "<u><font size=\"4\">" + team2.getName() + "</font></u>";
        } else {
            winningTeam = team1;
            labelWithWinner = "<html><u><font size=\"4\">" + team1.getName() + "</font></u>" + " vs " + team2.getName();

        }
        return winningTeam;
    }

    public String gameLabel() {
        return gameLabel;
    }

    public String getLabelWithWinner() {
        return labelWithWinner;
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

    //playerNumber1 = player order in team 1
    //playerNumber2 = player order in team 2
    public void updateGame(int playerNumber1, int playerNumber2){
        teamPlayerHitsGiven1[playerNumber1] = team1.getPlayers().get(playerNumber1).getNumberOfHitsGiven();
        teamPlayerHitsTaken1[playerNumber1] = team1.getPlayers().get(playerNumber1).getNumberOfHitsTaken();
        teamPlayerHitsGiven2[playerNumber2] = team2.getPlayers().get(playerNumber2).getNumberOfHitsGiven();
        teamPlayerHitsTaken2[playerNumber2] = team2.getPlayers().get(playerNumber2).getNumberOfHitsTaken();
    }

    private void initiate(int[] hits){
        for(int i = 0; i < numberOfPlayers; i++){
            hits[i] = 0;
        }
    }

    public int getTeamScore(int teamNumber) {
        int score = 0;
        if(teamNumber == 1) {
            for (int i = 0; i < numberOfPlayers; i++) {
                score += teamPlayerHitsGiven1[i];
            }
        } else if(teamNumber == 2) {
            for (int i = 0; i < numberOfPlayers; i++) {
                score += teamPlayerHitsGiven2[i];
            }
        }
        return score;
    }

    public int[] getTeamPlayerHitsGiven(int teamNumber){
        if (teamNumber == 1) {
            return teamPlayerHitsGiven1;
        }
        //else teamNumber == 2
        return teamPlayerHitsGiven2;
    }

    public int[] getTeamPlayerHitsTaken(int teamNumber){
        if (teamNumber == 1) {
            return teamPlayerHitsTaken1;
        }
        //else teamNumber == 2
        return teamPlayerHitsTaken2;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

}
