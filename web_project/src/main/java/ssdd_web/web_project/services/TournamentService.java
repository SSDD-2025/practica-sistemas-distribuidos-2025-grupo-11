package ssdd_web.web_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Team;
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

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament createTournament(String name, LocalDate dateT, int givenPoints, double prizeMoney,
            String location, Surface surface, List<Long> matchIds) {

        List<Match> matches = matchRepository.findAllById(matchIds);

        for (Match match : matches) {
            match.setDateM(dateT);
            match.setSurface(surface);
        }

        Tournament tournament = new Tournament(name, dateT, givenPoints, prizeMoney, location, surface,
                matches);

        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public void deleteTournamentById(Long id) {

    }

}
