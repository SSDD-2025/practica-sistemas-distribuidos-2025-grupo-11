package ssdd_web.web_project.controller.RestControllers;

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
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    public TeamRestController(TeamMapper teamMapper, TeamService teamService) {
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamMapper.toDTOList(teamService.getAllTeams());
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        TeamDTO teamDTO = teamMapper.toDTO(team);
        return ResponseEntity.ok(teamDTO);
    }

    @PostMapping("/")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team savedTeam = teamService.createTeam(teamDTO.name(), teamDTO.player1().id(), teamDTO.player2().id());
        if (savedTeam == null) {
            return ResponseEntity.badRequest().build(); // Bad request if players are not valid
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(teamMapper.toDTO(savedTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TeamDTO>> getTeamsPaged(Pageable pageable) {
        Page<Team> teams = teamService.getAllTeamsPaged(pageable);
        return ResponseEntity.ok(teams.map(teamMapper::toDTO));
    }
}
