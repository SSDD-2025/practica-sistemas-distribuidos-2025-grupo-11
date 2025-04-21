package ssdd_web.web_project.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.DTO.MatchDTO;
import ssdd_web.web_project.DTO.MatchMapper;
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

    @Autowired
    private MatchMapper matchMapper;

    // get all matches
    public List<MatchDTO> getAllMatches() {
        return matchMapper.toDTOs(matchRepository.findAll());
    }

    // get all matches in date order
    public List<MatchDTO> getAllMatchesDateOrder() {
        return matchMapper.toDTOs(matchRepository.findByOrderByDateMAsc());
    }

    public MatchDTO getMatchById(Long id) {
        return matchMapper.toDTO(matchRepository.findById(id).orElse(null));
    }

    // create match
    public MatchDTO createMatch(Long homeTeamId, Long awayTeamId, LocalDate dateM, Surface surface) {
        Team homeTeam = teamRepository.findById(homeTeamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        Team awayTeam = teamRepository.findById(awayTeamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        homeTeam.setAvailable(false);
        awayTeam.setAvailable(false);
        Match match = new Match(homeTeam, awayTeam, dateM, surface);
        return matchMapper.toDTO(matchRepository.save(match));
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

    public Page<MatchDTO> getAllMatchesPaged(Pageable pageable) {
        return matchRepository.findAll(pageable).map(matchMapper::toDTO);
    }
}
