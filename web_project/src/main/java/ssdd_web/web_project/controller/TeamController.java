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
import ssdd_web.web_project.services.TeamService;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // see all players and select 2
    @GetMapping("/register")
    public String showCreateTeamForm(Model model) {
        List<Player> players = teamService.getAvailablePlayers();
        model.addAttribute("players", players);
        return "CreateTeam"; // html to pick players
    }

    // save team in database
    @PostMapping("/add")
    public String createTeam(@RequestParam String name, @RequestParam("player1Id") Long player1Id,
            @RequestParam("player2Id") Long player2Id) {
        teamService.createTeam(name, player1Id, player2Id);
        return "redirect:/home";
    }

    // list all teams
    @GetMapping("/list")
    public String listTeams(Model model, @RequestParam(required = false) Team team) {
        List<Team> teams = teamService.getAllTeamsByRanking();
        model.addAttribute("teams", teams);
        return "TeamsList";
    }

    // team stats show
    @GetMapping("/{id}")
    public String showTeamStats(@PathVariable Long id, Model model) {
        Team team = teamService.getTeamById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        model.addAttribute("team", team);
        return "TeamStats";
    }

    // delete team by id
    @PostMapping("/delete/{id}")
    public String deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return "redirect:/teams/list";
    }

    // get all available teams
    @GetMapping("/available")
    public List<Team> getAvailableteams() {
        return teamService.getAllAvailableTeams();
    }

}
