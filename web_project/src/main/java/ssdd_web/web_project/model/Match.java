package ssdd_web.web_project.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Match() {
    }

    public enum STATUS {
        ACCEPTED,
        REJECTED,
        PENDING
    };

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;

    private STATUS status;
    private LocalDate dateM;
    private Surface surface;
    private String score;

    @ManyToOne
    private Team winnerTeam;

    public Match(Team homeTeam, Team awayTeam, LocalDate dateM, Surface surface) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateM = dateM;
        this.surface = surface;
        this.status = STATUS.PENDING;
        this.score = "0-0";
        this.winnerTeam = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateM() {
        return dateM;
    }

    public void setDateM(LocalDate dateM) {
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
