package ssdd_web.web_project.DTO;

import java.time.LocalDate;

import ssdd_web.web_project.model.Surface;
public record MatchDTO(
        Long id,
        LocalDate dateM,
        Surface surface,
        TeamDTO homeTeam,
        TeamDTO awayTeam) {
}
