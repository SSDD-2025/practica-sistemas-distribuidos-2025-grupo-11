package ssdd_web.web_project.model;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Lob;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Player() {
    }

    public enum Hand {
        LEFT,
        RIGHT,
        BOTH
    }

    private int winsPlayer;
    private int lossesPlayer;
    private double winratePlayer;
    private String name;
    private String surname;
    private String citizenship;
    private int height;
    private double weight;
    private int age;

    @Lob
    private Blob playerImage;

    private int aces; // total number of aces of the player in one match
    private int doubleFaults; // total number of double faults of this player in one match
    private double acespm; // average value of aces per match of the player
    private double doubleFaultspm; // average value of double falts per match of the player

    @Enumerated(EnumType.STRING)
    private Hand bestHand;
    @Enumerated(EnumType.STRING)
    private Surface bestSurface;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Team team;

    //Constructor without the player image
    public Player(String namePlayer, String surname, String citizenship, int height, double weight, int age,
            Hand bestHand,
            Surface bestSurface) {
        this.name = namePlayer;
        this.surname = surname;
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
        this.team = null;
    }

    //Constructor with the player image
    public Player(String namePlayer, String surname, String citizenship, int height, double weight, int age,
            Hand bestHand,
            Surface bestSurface, 
            Blob playerImage) {
        this.name = namePlayer;
        this.surname = surname;
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
        this.playerImage = playerImage;
    }

    //Constructor with the player image
    public Player(String namePlayer, String surname, String citizenship, int height, double weight, int age,
            Hand bestHand,
            Surface bestSurface, 
            Blob playerImage) {
        this.name = namePlayer;
        this.surname = surname;
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
        this.playerImage = playerImage;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Blob getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(Blob playerImage) {
        this.playerImage = playerImage;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getDoubleFaults() {
        return doubleFaults;
    }

    public void setDoubleFaults(int doubleFaults) {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}