package lasertag.server;

import lasertag.data.PlayersDAO;
import lasertag.data.Team;
import lasertag.data.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class TeamsFrame extends JFrame{

    private JPanel mainPanel;
    private JPanel teamsPanel;
    private JPanel chosenTeamsPanel;
    private JPanel buttonsPanel;
    private JScrollPane scroll;

    private ServerGUI serverGUI;
    private NewTeamFrame newTeamFrame;
    private PlayersDAO dao;

    ArrayList<Team> teamsList;
    ArrayList<JCheckBox> teamCheckBoxesList;

    private GridBagConstraints constraints;

    TeamsFrame(ServerGUI serverGUI) {
        super("LaserTag : Teams");

        this.serverGUI = serverGUI;

        //Initiate the list of teams and the list of respective checkboxes
        dao = new PlayersDAO();
        teamsList = dao.getTeams();
        teamCheckBoxesList = new ArrayList<>(101);
        if (teamsList != null) {
            for(Team t : teamsList) {
                teamCheckBoxesList.add(makeCheckBoxFromTeam(t));
            }
        }

        setLayout(new GridBagLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                serverGUI.stopServer();
                dao.stopConnection();
                System.exit(0);
            }
        } );

        setMainPanel();
        addTeamsPanel();
        addChosenTeamsPanel();
        addButtonsPanel();

        //revalidate();
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(mainPanel, constraints);
    }

    private void addTeamsPanel() {
        teamsPanel = new JPanel();
        teamsPanel.setBackground(Color.cyan);
        teamsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        teamsPanel.setLayout(new BoxLayout(teamsPanel, BoxLayout.Y_AXIS));

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        scroll = new JScrollPane(teamsPanel);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scroll, constraints);

        for (int i = 0; i < teamsList.size(); i++) {
            if (!teamsList.get(i).isInChampionship()) {
                teamsPanel.add(teamCheckBoxesList.get(i));
            }
        }
    }

    private void addChosenTeamsPanel() {
        chosenTeamsPanel = new JPanel();
        chosenTeamsPanel.setBackground(Color.cyan);
        chosenTeamsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        chosenTeamsPanel.setLayout(new BoxLayout(chosenTeamsPanel, BoxLayout.Y_AXIS));

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;

        scroll = new JScrollPane(chosenTeamsPanel);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        for (int i = 0; i < teamsList.size(); i++) {
            if(teamsList.get(i).isInChampionship()) {
                chosenTeamsPanel.add(teamCheckBoxesList.get(i));
            }
        }
        mainPanel.add(scroll, constraints);
    }

    void moveToChampionship(JCheckBox checkBox) {
        teamsPanel.remove(checkBox);
        chosenTeamsPanel.add(checkBox);
        revalidate();
    }

    void moveFromChampionship(JCheckBox checkBox) {
        chosenTeamsPanel.remove(checkBox);
        teamsPanel.add(checkBox);
        revalidate();
    }

    private void addButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        buttonsPanel.setLayout(new GridLayout(0, 4));

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonsPanel, constraints);

        addButtons();
    }

    private void addButtons() {
        //******************************************************************** Button #1 : New team
        JButton newTeamButton = new JButton("New team");
        newTeamButton.setFocusPainted(false);
        newTeamButton.setFocusable(false);
        newTeamButton.setFont(new Font("Arial", Font.PLAIN, 18));
        newTeamButton.addActionListener(new GUIButtonListener(GUIButtonListener.NEW_TEAM, this));
        buttonsPanel.add(newTeamButton);

        //******************************************************************** Button #2 : Add team
        JButton addTeamButton = new JButton("Add team");
        addTeamButton.setFocusPainted(false);
        addTeamButton.setFocusable(false);
        addTeamButton.setFont(new Font("Arial", Font.PLAIN, 18));
        addTeamButton.addActionListener(new GUIButtonListener(GUIButtonListener.ADD_TEAM, this));
        buttonsPanel.add(addTeamButton);

        //******************************************************************** Button #3 : Remove team
        JButton removeTeamButton = new JButton("Remove team");
        removeTeamButton.setFocusPainted(false);
        removeTeamButton.setFocusable(false);
        removeTeamButton.setFont(new Font("Arial", Font.PLAIN, 18));
        removeTeamButton.addActionListener(new GUIButtonListener(GUIButtonListener.REMOVE_TEAM, this));
        buttonsPanel.add(removeTeamButton);

        //******************************************************************** Button #4 : Start championship
        JButton startChampionshipButton = new JButton("<html>Start<br/>championship</html>");
        startChampionshipButton.setFocusPainted(false);
        startChampionshipButton.setFont(new Font("Arial", Font.PLAIN, 18));
        startChampionshipButton.addActionListener(new GUIButtonListener(GUIButtonListener.START_CHAMPIONSHIP, this, serverGUI));
        buttonsPanel.add(startChampionshipButton);
    }

    private JCheckBox makeCheckBoxFromTeam(Team team) {
        JCheckBox checkBox = new JCheckBox(team.getName(), false);
        checkBox.setBackground(Color.cyan);
        return checkBox;
    }

    void update() {

        remove(mainPanel);

        for (int i = teamCheckBoxesList.size(); i < teamsList.size(); i++) {
            teamCheckBoxesList.add(makeCheckBoxFromTeam(teamsList.get(i)));
        }

        setMainPanel();
        addTeamsPanel();
        addChosenTeamsPanel();
        addButtonsPanel();

        revalidate();
        repaint();
    }

    void startNewTeamsFrame(){
        if(newTeamFrame != null) {
            newTeamFrame.dispose();
        }
        newTeamFrame = new NewTeamFrame(this);
        newTeamFrame.start();
    }

    void addToDatabase(Player player) {
        dao.addPlayer(player);
    }

    void addToDatabase(Team team) {
        dao.addTeam(team);
    }
}
