package ssdd_web.web_project.controller.web;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.TeamDTO;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.TeamService;
import ssdd_web.web_project.services.UserService;

@Controller
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    // see all players and select 2
    @GetMapping("/register")
    public String showCreateTeamForm(Model model) {
        List<PlayerDTO> players = teamService.getAvailablePlayers();
        model.addAttribute("players", players);
        return "CreateTeam"; // html to pick players
    }

    // save team in database
    @PostMapping("/add")
    public String createTeam(@RequestParam String name, @RequestParam("player1Id") Long player1Id,
            @RequestParam("player2Id") Long player2Id) {
        User manager = userService.getUser().orElse(null);
        teamService.createTeam(name, player1Id, player2Id, manager);
        return "redirect:/home";
    }

    // list all teams
    @GetMapping("/list")
    public String listTeams(Model model, @RequestParam(required = false) Team team) {
        List<TeamDTO> teams = teamService.getAllTeamsByRanking();
        model.addAttribute("teams", teams);
        return "TeamsList";
    }

    // team stats show
    @GetMapping("/{id}")
    public String showTeamStats(@PathVariable Long id, Model model) {
        TeamDTO team = teamService.getTeamById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        model.addAttribute("team", team);
        return "TeamStats";
    }

    // delete team by id
    @PostMapping("/delete/{id}")
    public String deleteTeamById(@PathVariable Long id, HttpServletRequest request) {
        User loggedUser = userService.getUser().orElse(null);
        Team team = teamService.getTeamByIdEntity(id).orElseThrow(() -> new RuntimeException("Team not found"));

        boolean isAdmin = request.isUserInRole("ADMIN");
        boolean isOwner = team.getManager() != null && team.getManager().getId().equals(loggedUser.getId());

        if (!isAdmin && !isOwner) {
            return "redirect:/error";
        }

        loggedUser.setTeam(null);
        team.setManager(null);
        teamService.deleteTeamById(id);
        return "redirect:/teams/list";
    }

}
