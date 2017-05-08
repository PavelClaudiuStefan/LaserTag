package lasertag.data;

import java.util.ArrayList;

/**
 * Championship round
 * Round > Games > Teams > Players
 */
public class Round {

    private ArrayList<Game> games;      //List of games (between 2 teams)
    private ArrayList<Team> winners;    //List of teams that advance in the next round

    public Round(ArrayList<Game> games) {
        this.games = games;
        winners = new ArrayList<>();
    }

    public ArrayList<Team> getWinners() {
        for(Game game : games) {
            if (game.hasWinner()) {
                winners.add(game.getWinner());
            }
        }
        return winners;
    }

    public Game getGame(int nr) {
        return games.get(nr);
    }

    public int getNumberOfGames() {
        return games.size();
    }
}
