package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Surface;
import java.time.LocalDate;
import java.util.List;

public record TournamentDTO(
                Long id,
                String name,
                String location,
                LocalDate dateT,
                int givenPoints,
                double prizeMoney,
                Surface surface,
                List<MatchDTO> matches) {
}
