package ssdd_web.web_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.repository.MatchRepository;
import ssdd_web.web_project.repository.TeamRepository;
import ssdd_web.web_project.repository.TournamentRepository;
import java.time.LocalDate;
import java.util.*;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Transactional
    public Tournament createTournament(String name, LocalDate dateT, int givenPoints, double prizeMoney,
            String location, Surface surface, List<Long> matchIds) {

        List<Match> matches = matchRepository.findAllById(matchIds);

        for (Match match : matches) {
            match.setDateM(dateT);
            match.setSurface(surface);
            matchRepository.save(match);
        }

        Tournament tournament = new Tournament(name, dateT, givenPoints, prizeMoney, location, surface,
                matches);

        for (Match match : matches) {
            match.setTournament(tournament);
            matchRepository.save(match);
        }

        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    @Transactional
    public void deleteTournamentById(Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);

        if (tournament.isPresent()) {
            Tournament actual = tournament.get();
            for (Match match : actual.getMatches()) {
                if (match.getHomeTeam() != null) {
                    match.getHomeTeam().setAvailable(true);
                    teamRepository.save(match.getHomeTeam());
                }
                if (match.getAwayTeam() != null) {
                    match.getAwayTeam().setAvailable(true);
                    teamRepository.save(match.getAwayTeam());
                }
            }

            tournamentRepository.delete(actual);
        }
    }

}
