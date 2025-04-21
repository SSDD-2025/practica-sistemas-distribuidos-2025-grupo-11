package ssdd_web.web_project.controller.rest;

import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.TeamDTO;
import ssdd_web.web_project.DTO.TeamMapper;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.services.TeamService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

    private final TeamService teamService;

    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        TeamDTO team = teamService.getTeamById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(team);
    }

    @PostMapping("/")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        TeamDTO savedTeam = teamService.createTeam(teamDTO.name(), teamDTO.player1().id(), teamDTO.player2().id());
        if (savedTeam == null) {
            return ResponseEntity.badRequest().build(); // Bad request if players are not valid
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TeamDTO>> getTeamsPaged(Pageable pageable) {
        Page<TeamDTO> teams = teamService.getAllTeamsPaged(pageable);
        return ResponseEntity.ok(teams);
    }
}
