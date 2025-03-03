package ssdd_web.web_project.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Tournament() {
    }

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches;

    private LocalDate dateT;
    private double prizeMoney;
    private int givenPoints;
    private Surface surface;
    private String location;
    private String name;

    public Tournament(String name, LocalDate dateT, int givenPoints, double prizeMoney, String location,
            Surface surface, List<Match> matches) {
        this.name = name;
        this.dateT = dateT;
        this.givenPoints = givenPoints;
        this.location = location;
        this.matches = matches;
        this.prizeMoney = prizeMoney;
        this.surface = surface;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public LocalDate getDateT() {
        return dateT;
    }

    public void setDateT(LocalDate dateT) {
        this.dateT = dateT;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
