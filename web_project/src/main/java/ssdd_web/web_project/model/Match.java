package ssdd_web.web_project.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gameMatch")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Team homeTeam;

    @OneToOne
    private Team awayTeam;

    private LocalDate dateM;
    private Surface surface;
    private String score;

    @OneToOne
    private Team winnerTeam;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public Match() {
    }

    public Match(Team homeTeam, Team awayTeam, LocalDate dateM, Surface surface) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.dateM = dateM;
        this.surface = surface;
        this.score = "0-0";
        this.winnerTeam = null;
        this.tournament = null;
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

    public double ChanceOfWining() { // Temporary implementation, think of a formula to calculate the chance of
                                     // winning
        double chance = 0.5;
        return chance;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
