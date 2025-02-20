package ssdd_web.web_project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Entity
@Table(name = "tournament")
public class Tournament {
    private ArrayList<Match> matches;
    private SimpleDateFormat dateT;
    private ArrayList<Team> ranking;
    private double prizeMoney;
    private int givenPoints;

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public SimpleDateFormat getDateT() {
        return dateT;
    }

    public void setDateT(SimpleDateFormat dateT) {
        this.dateT = dateT;
    }

    public ArrayList<Team> getRanking() {
        return ranking;
    }

    public void setRanking(ArrayList<Team> ranking) {
        this.ranking = ranking;
    }

    public double getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(double prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public int getGivenPoints() {
        return givenPoints;
    }

    public void setGivenPoints(int givenPoints) {
        this.givenPoints = givenPoints;
    }

    
    
}
