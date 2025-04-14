package ssdd_web.web_project.DTO;

public record MatchDTO(
        String id,
        String name,
        String description,
        String date,
        String time,
        String location,
        String team1,
        String team2) {
}
