package lasertag.data;

import java.util.ArrayList;

//DataAccesObject
/*

    Player:
    -int id;
    -String name;
    -int teamID;

    Team:
    -int id;
    -String name

    public ArrayList<Player> getPlayers()
    public void addPlayer(Player player)
    public void setPlayer(int playerID)
    public void deletePlayer(int playerID)

    Mai avem nevoie de chestii, dar asta e minimul


 */
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
        //Create players for testing
        for (int i = 0; i < 34; i++) {
            Player player = new Player(i, "Player #" + i);
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
        for(int i = 0; i < playerList.size()-1; i += 2) {
            Team team = new Team("Team #" + (i/2+1));
            team.addPlayer(playerList.get(i));
            team.addPlayer(playerList.get(i+1));
            teamsList.add(team);
        }

        for (int i = 0; i  < 16; i++) {
            teamsList.get(i).setInChampionship(true);
        }

        return teamsList;
    }

}
