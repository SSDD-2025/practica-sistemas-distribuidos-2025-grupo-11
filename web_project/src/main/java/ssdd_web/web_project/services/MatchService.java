package ssdd_web.web_project.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.repository.MatchRepository;
import ssdd_web.web_project.repository.TeamRepository;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    // get all matches
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // get all matches in date order
    public List<Match> getAllMatchesDateOrder() {
        return matchRepository.findByOrderByDateMAsc();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    // create match
    public Match createMatch(Long homeTeamId, Long awayTeamId, LocalDate dateM, Surface surface) {
        Team homeTeam = teamRepository.findById(homeTeamId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        Team awayTeam = teamRepository.findById(awayTeamId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        homeTeam.setAvailable(false);
        awayTeam.setAvailable(false);
        Match match = new Match(homeTeam, awayTeam, dateM, surface);
        return matchRepository.save(match);
    }

    // update match
    public Match updateMatch(Long matchId, String score, Long winnerTeamId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        // setters and more
        return matchRepository.save(match);
    }

    // delete match by id
    public void deleteMatchById(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isPresent()) {
            match.get().getHomeTeam().setAvailable(true);
            match.get().getAwayTeam().setAvailable(true);
            matchRepository.deleteById(id);
        }
    }
}
