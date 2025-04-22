package ssdd_web.web_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssdd_web.web_project.DTO.MatchDTO;
import ssdd_web.web_project.DTO.MatchMapper;
import ssdd_web.web_project.DTO.TournamentDTO;
import ssdd_web.web_project.DTO.TournamentMapper;
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

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private TournamentMapper tournamentMapper;

    public List<MatchDTO> getAllMatches() {
        return matchMapper.toDTOs(matchRepository.findAll());
    }

    public List<TournamentDTO> getAllTournaments() {
        return tournamentMapper.toDTOs(tournamentRepository.findAll());
    }

    @Transactional
    public TournamentDTO createTournament(String name, LocalDate dateT, int givenPoints, double prizeMoney,
            String location, Surface surface, List<Long> matchIds) {

        List<Match> matches = matchRepository.findAllById(matchIds);
        Tournament tournament = new Tournament(name, dateT, givenPoints, prizeMoney, location, surface, matches);

        for (Match match : matches) {
            match.setDateM(dateT);
            match.setSurface(surface);
            match.setTournament(tournament);
            matchMapper.toDTO(matchRepository.save(match));
        }

        return tournamentMapper.toDTO(tournamentRepository.save(tournament));
    }

    public TournamentDTO getTournamentById(Long id) {
        return tournamentMapper.toDTO(tournamentRepository.findById(id).orElse(null));
    }

    public List<MatchDTO> getTournamentMatches(Long id) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        return matchMapper.toDTOs(tournament.getMatches());

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

    public Page<TournamentDTO> getAllTournamentsPaged(Pageable pageable) {
        return tournamentRepository.findAll(pageable).map(tournamentMapper::toDTO);
    }

}
