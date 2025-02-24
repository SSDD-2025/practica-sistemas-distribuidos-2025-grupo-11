package ssdd_web.web_project.model;

import java.text.SimpleDateFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "match")
public class Match {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Match() {}

    public enum STATUS {
        ACCEPTED,
        REJECTED,
        PENDING
    };

    private Team homeTeam;
    private Team awayTeam;
    private STATUS status;
    private SimpleDateFormat dateM;
    private Surface surface;
    private String score;
    private Team winnerTeam;

    
    public Match(Team homeTeam, Team awayTeam, SimpleDateFormat dateM, Surface surface) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateM = dateM;
        this.surface = surface;
        this.status = null;
        this.score = "0-0";
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public SimpleDateFormat getDateM() {
        return dateM;
    }

    public void setDateM(SimpleDateFormat dateM) {
        this.dateM = dateM;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    
    

}
