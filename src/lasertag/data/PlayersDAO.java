package lasertag.data;

import java.util.ArrayList;

public class PlayersDAO {

    ArrayList<Player> playerList;
    ArrayList<Team> teamsList;

    public PlayersDAO() {
        updatePlayerList();
    }

    public void updatePlayerList() {
        playerList = new ArrayList<>(101);

        //TODO Read players from a database
        //TEMPORAR
        //Create 10 players for testing
        for (int i = 0; i < 10; i++) {
            Player player = new Player(i, "Player #" + i + 1);
            playerList.add(player);
        }
    }

    public ArrayList<Player> getPlayers() {
        return playerList;
    }

    public ArrayList<Team> getTeams() {
        teamsList = new ArrayList<>(101);

        //TEMPORAR
        //Create teams with the existent temporar players
        for(int i = 0; i < playerList.size(); i += 2) {
            Team team = new Team("Team #" + (i/2+1));
            team.addPlayer(playerList.get(i));
            team.addPlayer(playerList.get(i+1));
            teamsList.add(team);
        }

        teamsList.get(1).setInChampionship(true);

        return teamsList;
    }

}
