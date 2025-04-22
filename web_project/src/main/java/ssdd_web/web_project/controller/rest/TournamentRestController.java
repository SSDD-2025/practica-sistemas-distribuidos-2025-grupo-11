package ssdd_web.web_project.controller.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ssdd_web.web_project.DTO.TournamentDTO;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentRestController {

    TournamentService tournamentService;

    public TournamentRestController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentDTO>> getAllTournaments() {
        List<TournamentDTO> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")

    @PostMapping
    public ResponseEntity<TournamentDTO> createTournament(@RequestBody TournamentDTO tournamentDTO) {
        // We do this to get the IDs of the matches to create the tournament
        List<Long> matchIds = tournamentService.getTournamentMatches(tournamentDTO.id()).stream()
                .map(match -> match.id()).collect(Collectors.toList());

        TournamentDTO tournament = tournamentService.createTournament(tournamentDTO.name(), tournamentDTO.dateT(),
                tournamentDTO.givenPoints(), tournamentDTO.prizeMoney(), tournamentDTO.location(),
                tournamentDTO.surface(), matchIds);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tournament.id())
                .toUri();

        return ResponseEntity.created(location).body(tournament);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournamentById(@PathVariable Long id) {
        tournamentService.deleteTournamentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TournamentDTO>> getTournamentsPaged(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<TournamentDTO> tournaments = tournamentService.getAllTournamentsPaged(pageable);
        return ResponseEntity.ok(tournaments);
    }
}
