package lasertag.server;

import lasertag.data.Team;

import java.util.ArrayList;

public class ServerGUI {

    private Server server;
    ChampionshipFrame championshipFrame;

    public ServerGUI(String host, int port) {
        server = new Server(host, port, this);
    }

    public void start() {
        new ServerRunning().start();
        TeamsFrame teamsFrame = new TeamsFrame(this);
        teamsFrame.revalidate();
    }

    public void startChampionship(ArrayList<Team> teamsList) {
        championshipFrame = new ChampionshipFrame(teamsList);
        championshipFrame.revalidate();
    }

    public void sendInfoToChampionshipFrame(int source, int target) {
        championshipFrame.debugInfo(source, target);
        //TODO : Update game score;
        championshipFrame.updateGameScore(source, target);
    }

    class ServerRunning extends Thread {
        public void run() {
            // should execute until if fails
            server.start();
            // the server failed
            server = null;
        }
    }

}