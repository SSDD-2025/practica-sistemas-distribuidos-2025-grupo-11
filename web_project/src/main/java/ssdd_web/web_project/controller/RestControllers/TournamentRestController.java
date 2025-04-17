package ssdd_web.web_project.controller.RestControllers;

import org.springframework.web.bind.annotation.RestController;

import ssdd_web.web_project.DTO.TournamentDTO;
import ssdd_web.web_project.DTO.TournamentMapper;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.services.TournamentService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {
    TournamentService tournamentService;
    TournamentMapper tournamentMapper;

    public TournamentRestController(TournamentService tournamentService, TournamentMapper tournamentMapper) {
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
    }

    @GetMapping
    public ResponseEntity<List<TournamentDTO>> getAllTournaments() {
        List<TournamentDTO> tournaments = tournamentMapper.toDtoList(tournamentService.getAllTournaments());
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")

    @PostMapping
    public ResponseEntity<TournamentDTO> createTournament(@RequestBody TournamentDTO tournamentDTO) {
        List<Long> matchIds = tournamentDTO.matches().stream()  // We do this to get the IDs of the matches to create the tournament
                .map(match -> match.id()) 
                .collect(Collectors.toList());

        Tournament tournament = tournamentService.createTournament(
                tournamentDTO.name(),
                tournamentDTO.dateT(),
                tournamentDTO.givenPoints(),
                tournamentDTO.prizeMoney(),
                tournamentDTO.location(),
                tournamentDTO.surface(),
                matchIds 
        );

        TournamentDTO responseDTO = tournamentMapper.toDto(tournament);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournamentById(@PathVariable Long id) {
        tournamentService.deleteTournamentById(id);
        return ResponseEntity.noContent().build();
    }
}
