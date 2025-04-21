package ssdd_web.web_project.controller.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ssdd_web.web_project.DTO.MatchCreateDTO;
import ssdd_web.web_project.DTO.MatchDTO;
import ssdd_web.web_project.DTO.MatchMapper;
import ssdd_web.web_project.DTO.TeamDTO;
import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.services.MatchService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    private final MatchService matchService;

    public MatchRestController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        MatchDTO match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchCreateDTO dto) {
        MatchDTO match = matchService.createMatch(dto.homeTeamId(), dto.awayTeamId(), dto.dateM(), dto.surface());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(match);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<MatchDTO>> getMatchesPaged(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<MatchDTO> matches = matchService.getAllMatchesPaged(pageable);
        return ResponseEntity.ok(matches);
    }
}