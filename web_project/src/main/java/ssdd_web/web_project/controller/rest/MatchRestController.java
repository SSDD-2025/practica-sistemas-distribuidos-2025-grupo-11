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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ssdd_web.web_project.DTO.MatchCreateDTO;
import ssdd_web.web_project.DTO.MatchDTO;
import ssdd_web.web_project.DTO.MatchMapper;
import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.services.MatchService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchRestController {

    private final MatchService matchService;
    private final MatchMapper matchMapper;

    public MatchRestController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getAllMatches() {
        List<MatchDTO> matches = matchMapper.toDtoList(matchService.getAllMatches());
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MatchDTO matchDTO = matchMapper.toDto(match);
        return ResponseEntity.ok(matchDTO);
    }

    @PostMapping
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchCreateDTO dto) {
        Match match = matchService.createMatch(dto.homeTeamId(), dto.awayTeamId(), dto.dateM(), dto.surface());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(match.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(matchMapper.toDto(match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.noContent().build();
    }
}