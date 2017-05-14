package lasertag.server;

import lasertag.data.Team;


import java.util.ArrayList;

public class ServerGUI {

    private Server server;
    private ChampionshipFrame championshipFrame;

    public ServerGUI(int port) {
        server = new Server(port, this);
    }

    public void start() {
        new ServerRunning().start();
        TeamsFrame teamsFrame = new TeamsFrame(this);
        teamsFrame.revalidate();
    }

    void startChampionship(ArrayList<Team> teamsList) {
        championshipFrame = new ChampionshipFrame(teamsList);
        championshipFrame.revalidate();
    }

    void sendInfoToChampionshipFrame(int source, int target) {
        championshipFrame.update(source, target);
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