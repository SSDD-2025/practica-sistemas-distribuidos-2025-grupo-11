package ssdd_web.web_project;
import java.util.ArrayList;

public class User {
    private String name;
    private Team team;
    private ArrayList<Team> formerTeams;
    private double winrate;
    private int winsUser;
    private int lossesUser;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }   
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }
    
    public ArrayList<Team> getFormerTeams() {
        return formerTeams;
    }
    
    public void setFormerTeams(ArrayList<Team> formerTeams) {
        this.formerTeams = formerTeams;
    }
    
    public double getWinrate() {
        return winrate;
    }
    
    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }
    
    public int getWinsUser() {
        return winsUser;
    }
    
    public void setWinsUser(int winsUser) {
        this.winsUser = winsUser;
    }
    
    public int getLossesPlayer() {
        return lossesUser;
    }
    
    public void setLossesPlayer(int lossesUser) {
        this.lossesUser = lossesUser;
    }
    
    
}

