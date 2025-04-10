package ssdd_web.web_project.DTO;

public class MatchDTO {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private String dateM;
    private String surface;
    private String score;
    private Long winnerTeamId;
    private Long tournamentId;

    public MatchDTO() {}

    public MatchDTO(Long id, Long homeTeamId, Long awayTeamId, String dateM, String surface, String score, Long winnerTeamId, Long tournamentId) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.dateM = dateM;
        this.surface = surface;
        this.score = score;
        this.winnerTeamId = winnerTeamId;
        this.tournamentId = tournamentId;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(Long homeTeamId) { this.homeTeamId = homeTeamId; }
    public Long getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(Long awayTeamId) { this.awayTeamId = awayTeamId; }
    public String getDateM() { return dateM; }
    public void setDateM(String dateM) { this.dateM = dateM; }
    public String getSurface() { return surface; }
    public void setSurface(String surface) { this.surface = surface; }
    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }
    public Long getWinnerTeamId() { return winnerTeamId; }
    public void setWinnerTeamId(Long winnerTeamId) { this.winnerTeamId = winnerTeamId; }
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
}
