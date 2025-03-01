package ssdd_web.web_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.repository.PlayerRepository;
import ssdd_web.web_project.services.PlayerService;
import ssdd_web.web_project.services.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    // see all players and select 2
    @GetMapping("/team/select")
    public String showCreateTeamForm(Model model) {
        List<Player> players = teamService.getAllPlayers();
        model.addAttribute("players", players);
        return "CreateTeam"; // html to pick players
    }

    @PostMapping("/team/create")
    public String createTeam(@RequestParam Long player1Id, @RequestParam Long player2Id) {
        Team team = teamService.createTeam(player1Id, player2Id);
        return "redirect:/teams" + team.getId();
    }

    // team stats show
    @GetMapping("/{id}")
    public String showTeamStats(@PathVariable Long id, Model model) {
        Team team = teamService.getTeamById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        model.addAttribute("team", team);
        return "TeamStats";
    }

}
