package ssdd_web.web_project;

import java.util.ArrayList;


public class Team {
    private Player player1;
    private Player player2;
    private int winsTeam;
    private int lossesTeam;
    private double winrateTeam;
    private User manager;
    private int ranking;
    private ArrayList<Tournament> titles;
    
    public Player getPlayer1() {
        return player1;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public int getWinsTeam() {
        return winsTeam;
    }
    public void setWinsTeam(int winsTeam) {
        this.winsTeam = winsTeam;
    }
    public int getLossesTeam() {
        return lossesTeam;
    }
    public void setLossesTeam(int lossesTeam) {
        this.lossesTeam = lossesTeam;
    }
    public double getWinrateTeam() {
        return winrateTeam;
    }
    public void setWinrateTeam(double winrateTeam) {
        this.winrateTeam = winrateTeam;
    }
    public User getManager() {
        return manager;
    }
    public void setManager(User manager) {
        this.manager = manager;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public ArrayList<Tournament> getTitles() {
        return titles;
    }
    public void setTitles(ArrayList<Tournament> titles) {
        this.titles = titles;
    }

    

}
