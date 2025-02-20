package ssdd_web.web_project.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Tournament() {}

    private ArrayList<Match> matches;
    private SimpleDateFormat dateT;
    private ArrayList<Team> ranking;
    private double prizeMoney;
    private int givenPoints;
    private Surface surface;
    private String location;

    public Tournament(SimpleDateFormat dateT, int givenPoints, String location, ArrayList<Match> matches, double prizeMoney, Surface surface) {
        this.dateT = dateT;
        this.givenPoints = givenPoints;
        this.location = location;
        this.matches = matches;
        this.prizeMoney = prizeMoney;
        this.surface = surface;
        this.ranking = new ArrayList<>();
    }


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

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
    
}
