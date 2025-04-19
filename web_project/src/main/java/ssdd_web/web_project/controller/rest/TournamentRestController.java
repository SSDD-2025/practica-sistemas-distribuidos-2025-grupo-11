package ssdd_web.web_project.controller.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ssdd_web.web_project.DTO.TournamentDTO;
import ssdd_web.web_project.DTO.TournamentMapper;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.services.TournamentService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        List<Long> matchIds = tournamentDTO.matches().stream() // We do this to get the IDs of the matches to create the
                                                               // tournament
                .map(match -> match.id())
                .collect(Collectors.toList());

        Tournament tournament = tournamentService.createTournament(
                tournamentDTO.name(),
                tournamentDTO.dateT(),
                tournamentDTO.givenPoints(),
                tournamentDTO.prizeMoney(),
                tournamentDTO.location(),
                tournamentDTO.surface(),
                matchIds);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tournament.getId())
                .toUri();

        TournamentDTO responseDTO = tournamentMapper.toDTO(tournament);
        return ResponseEntity
                .created(location)
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournamentById(@PathVariable Long id) {
        tournamentService.deleteTournamentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TournamentDTO>> getTournamentsPaged(Pageable pageable) {
        Page<Tournament> tournaments = tournamentService.getAllTournamentsPaged(pageable);
        return ResponseEntity.ok(tournaments.map(tournamentMapper::toDTO));
    }
}
