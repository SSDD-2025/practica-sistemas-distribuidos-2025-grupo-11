package ssdd_web.web_project;

@Entity
@Table(name = "Player")
public class Player {
    private enum Hand {
        LEFT,
        RIGHT,
        BOTH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int winsPlayer;
    private int lossesPlayer;
    private double winratePlayer;

    @Column(nullable = false)
    private String namePlayer;

    @Column(nullable = false)
    private String citizenship;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int age;

    private double aces; // total number of aces of the player in one match
    private double doubleFaults; // total number of double faults of this player in one match
    private double acespm; // average value of aces per match of the player
    private double doubleFaultspm; // average value of double falts per match of the player

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Hand bestHand;

    @Column(nullable = false)
    private surface bestSurface;

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

    public void setWinratePlayer(double winrate) {
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

    public surface getBestSurface() {
        return bestSurface;
    }

    public void setBestSurface(surface bestSurface) {
        this.bestSurface = bestSurface;
    }

}