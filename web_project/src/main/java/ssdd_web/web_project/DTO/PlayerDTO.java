package ssdd_web.web_project.DTO;

public class PlayerDTO {

    private Long id;
    private String name;
    private String surname;
    private String citizenship;
    private int height;
    private double weight;
    private int age;
    private String bestHand;
    private String bestSurface;
    private int winsPlayer;
    private int lossesPlayer;
    private double winratePlayer;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String name, String surname, String citizenship, int height, double weight, int age,
            String bestHand, String bestSurface, int winsPlayer, int lossesPlayer, double winratePlayer) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.citizenship = citizenship;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.bestHand = bestHand;
        this.bestSurface = bestSurface;
        this.winsPlayer = winsPlayer;
        this.lossesPlayer = lossesPlayer;
        this.winratePlayer = winratePlayer;
    }

    // Getters y Setters
    public Long getId() {
        return id;
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

    public String getBestHand() {
        return bestHand;
    }

    public void setBestHand(String bestHand) {
        this.bestHand = bestHand;
    }

    public String getBestSurface() {
        return bestSurface;
    }

    public void setBestSurface(String bestSurface) {
        this.bestSurface = bestSurface;
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
}