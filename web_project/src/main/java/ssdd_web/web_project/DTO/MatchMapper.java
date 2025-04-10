package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.Tournament;
import java.time.LocalDate;

public class MatchMapper {
    public static MatchDTO toDTO(Match match) {
        return new MatchDTO(
            match.getId(),
            match.getHomeTeam().getId(),
            match.getAwayTeam().getId(),
            match.getDateM().toString(),
            match.getSurface().toString(),
            match.getScore(),
            (match.getWinnerTeam() != null ? match.getWinnerTeam().getId() : null),
            (match.getTournament() != null ? match.getTournament().getId() : null)
        );
    }

    public static Match toEntity(MatchDTO matchDTO, Team homeTeam, Team awayTeam, Team winnerTeam, Tournament tournament) {
        Match match = new Match(
            homeTeam,
            awayTeam,
            LocalDate.parse(matchDTO.getDateM()),
            Surface.valueOf(matchDTO.getSurface())
        );
        match.setId(matchDTO.getId());
        match.setScore(matchDTO.getScore());
        match.setWinnerTeam(winnerTeam);
        match.setTournament(tournament);
        return match;
    }
}
