package ssdd_web.web_project.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.repository.TeamRepository;
import ssdd_web.web_project.repository.TournamentRepository;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TeamRepository teamRepository;

    // all teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // create new tournament
    public Tournament createTournament(LocalDate dateT, int givenPoints, String location, double prizeMoney,
            Surface surface, List<Long> teamIds) {
        Tournament tournament = new Tournament(dateT, givenPoints, location, List.of(), prizeMoney, surface);
        return tournamentRepository.save(tournament);
    }

    // all tournaments
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }
}
