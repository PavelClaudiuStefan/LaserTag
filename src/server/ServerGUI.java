package server;

public class ServerGUI {

    private Server server;

    public ServerGUI(String host, int port) {
        server = new Server(host, port, this);
    }

    public void start() {
        new ServerRunning().start();
        TeamsFrame teamsFrame = new TeamsFrame();
        teamsFrame.revalidate();
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