package ssdd_web.web_project.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Team() {
    }

    @OneToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    private int winsTeam;
    private int lossesTeam;
    private double winrateTeam;
    private String name;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Match> awayMatches;

    /*
     * @OneToOne
     * private User manager;
     */

    private boolean available;
    private int ranking;
    private int points;

    public Team(String name, Player player1, Player player2) {
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.winsTeam = 0;
        this.lossesTeam = 0;
        this.winrateTeam = 0;
        this.ranking = 1;
        this.points = 0;
        this.homeMatches = null;
        this.awayMatches = null;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    /*
     * public User getManager() {
     * return manager;
     * }
     * 
     * public void setManager(User manager) {
     * this.manager = manager;
     * }
     */

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getHomeMatches() {
        return homeMatches;
    }

    public void setHomeMatches(List<Match> homeMatches) {
        this.homeMatches = homeMatches;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
