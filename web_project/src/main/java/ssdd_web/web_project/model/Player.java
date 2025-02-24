package ssdd_web.web_project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    public Player() {}

    public enum Hand {
        LEFT,
        RIGHT,
        BOTH
    }

    private int winsPlayer;
    private int lossesPlayer;
    private double winratePlayer;
    private String namePlayer;
    private String citizenship;
    private int height;
    private double weight;
    private int age;

    private double aces; // total number of aces of the player in one match
    private double doubleFaults; // total number of double faults of this player in one match
    private double acespm; // average value of aces per match of the player
    private double doubleFaultspm; // average value of double falts per match of the player

    private Hand bestHand;
    private Surface bestSurface;

    public Player(String namePlayer, String citizenship, int height, double weight, int age, Hand bestHand,
            Surface bestSurface) {
        this.namePlayer = namePlayer;
        this.citizenship = citizenship;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.bestHand = bestHand;
        this.bestSurface = bestSurface;
        this.winsPlayer = 0;
        this.lossesPlayer = 0;
        this.winratePlayer = 0;
        this.aces = 0;
        this.doubleFaults = 0;
        this.acespm = 0;
        this.doubleFaultspm = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWinsPlayer() {
        return winsPlayer;
    }

    public void setWinsPlayer(int winsPlayer) {
        this.winsPlayer = winsPlayer;
    }

    public int getLossesPlayer() {
        return lossesPlayer;
    }

    public void setLossesPlayer(int lossesPlayer) {
        this.lossesPlayer = lossesPlayer;
    }

    public double getWinratePlayer() {
        return winratePlayer;
    }

    public void setWinratePlayer(double winratePlayer) {
        this.winratePlayer = winratePlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAces() {
        return aces;
    }

    public void setAces(double aces) {
        this.aces = aces;
    }

    public double getDoubleFaults() {
        return doubleFaults;
    }

    public void setDoubleFaults(double doubleFaults) {
        this.doubleFaults = doubleFaults;
    }

    public double getAcespm() {
        return acespm;
    }

    public void setAcespm(double acespm) {
        this.acespm = acespm;
    }

    public double getDoubleFaultspm() {
        return doubleFaultspm;
    }

    public void setDoubleFaultspm(double doubleFaultspm) {
        this.doubleFaultspm = doubleFaultspm;
    }

    public Hand getBestHand() {
        return bestHand;
    }

    public void setBestHand(Hand bestHand) {
        this.bestHand = bestHand;
    }

    public Surface getBestSurface() {
        return bestSurface;
    }

    public void setBestSurface(Surface bestSurface) {
        this.bestSurface = bestSurface;
    }

}