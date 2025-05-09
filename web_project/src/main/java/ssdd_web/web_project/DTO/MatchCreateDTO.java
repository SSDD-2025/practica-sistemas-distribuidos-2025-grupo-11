package ssdd_web.web_project.DTO;

import java.time.LocalDate;
import ssdd_web.web_project.model.Surface;

public record MatchCreateDTO(
                Long homeTeamId,
                Long awayTeamId,
                LocalDate dateM,
                Surface surface) {
}