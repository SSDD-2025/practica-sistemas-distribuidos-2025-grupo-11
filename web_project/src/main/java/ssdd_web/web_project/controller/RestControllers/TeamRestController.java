package ssdd_web.web_project.controller.RestControllers;

import ssdd_web.web_project.DTO.TeamDTO;
import ssdd_web.web_project.DTO.TeamMapper;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.services.TeamService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*@GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id){
        Team team = teamService.getTeamById(id);
        if (team!= null){
            return ResponseEntity.ok(teamMapper.toDTO(team));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */
    @PostMapping("/")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        Team savedTeam = teamService.createTeam(teamDTO.name(), teamDTO.player1().id(), teamDTO.player2().id());
        if (savedTeam == null) {
            return ResponseEntity.badRequest().build(); // Bad request if players are not valid
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMapper.toDTO(savedTeam));
    }



}
