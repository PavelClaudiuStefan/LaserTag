package data;

import java.util.ArrayList;
import java.util.List;

public class PlayersDAO {

    public List<Player> getPlayers() {
        List<Player> playerList = new ArrayList<>();

        //TEMPORAR
        //Create 8 players for testing
        for (int i = 0; i < 8; i++) {
            Player player = new Player(i, "Player #" + i + 1);
            playerList.add(player);
        }

        return playerList;
    }

}
