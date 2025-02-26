package ssdd_web.web_project.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "user")
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public User() {};

    private String name;
    @OneToOne
    private Team team;
    private double winrate;
    private int winsUser;
    private int lossesUser;

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
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
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

