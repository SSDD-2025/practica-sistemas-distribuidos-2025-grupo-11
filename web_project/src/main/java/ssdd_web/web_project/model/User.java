package ssdd_web.web_project.model;

import java.sql.Blob;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User() {
    };

    /*
     * @OneToOne
     * private Team team;
     */

    private String name;
    private String password;
    private String email;
    private double winrate;
    private int winsUser;
    private int lossesUser;

    @ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

    public User(String name) {
        this.name = name;
        this.winrate = 0;
        this.winsUser = 0;
        this.lossesUser = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * public Team getTeam() {
     * return team;
     * }
     * 
     * public void setTeam(Team team) {
     * this.team = team;
     * }
     */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLossesUser() {
        return lossesUser;
    }

    public void setLossesUser(int lossesUser) {
        this.lossesUser = lossesUser;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setLossesPlayer(int lossesUser) {
        this.lossesUser = lossesUser;
    }

}
