package lasertag.data;

import java.sql.*;
import java.util.ArrayList;

public class PlayersDAO {

    private ArrayList<Player> players;
    private ArrayList<Team> teams;

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public PlayersDAO() {

        String dbUrl = "jdbc:mysql://localhost:3306/laser_tag_database?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "09041996";

        players = new ArrayList<>();
        teams = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(dbUrl, user, password);

            //Create playersList
            preparedStatement = connection.prepareStatement("select * from players");
            resultSet  = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int teamId = resultSet.getInt("teamId");
                players.add(new Player(id, name, teamId));
            }

            //Create teamsList
            preparedStatement = connection.prepareStatement("select * from teams");
            resultSet  = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                teams.add(new Team(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } /*finally {
            try { if (resultSet != null) resultSet.close(); } catch (Exception e) {e.printStackTrace();}
            try { if (preparedStatement != null) preparedStatement.close(); } catch (Exception e) {e.printStackTrace();}
            try { if (connection != null) connection.close(); } catch (Exception e) {e.printStackTrace();}
        }*/

        //Adding players to teams
        for (Player player : players) {
            teams.get(player.getTeamId()).addPlayer(player);
        }

    }

    public ArrayList<Team> getTeams() {

        //Debug
        for (int i = 0; i < 8; i++) {
            teams.get(i).setInChampionship(true);
        }

        return teams;
    }

    public void addPlayer(Player player) {
        try {
            int id = player.getId();
            String name = player.getName();
            int teamID = player.getTeamId();
            String sql = "INSERT INTO `laser_tag_database`.`players` (`ID`, `name`, `teamID`) VALUES ('"+id+"', '"+name+"', '"+teamID+"')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTeam(Team team){
        try {
            int id = team.getId();
            String name = team.getName();
            String sql = "INSERT INTO `laser_tag_database`.`teams` (`ID`, `name`) VALUES ('"+id+"', '"+name+"')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
