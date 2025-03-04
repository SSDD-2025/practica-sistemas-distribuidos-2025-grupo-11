package ssdd_web.web_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.services.MatchService;
import ssdd_web.web_project.services.TeamService;
import ssdd_web.web_project.services.TournamentService;

@Controller
public class WebController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/home")
    public String webString(Model model) {
        List<Match> matches = matchService.getAllMatchesDateOrder();
        List<Team> teams = teamService.getAllTeamsByRanking();
        List<Tournament> tournaments = tournamentService.getAllTournaments();

        List<Match> recentMatches = matches.stream().limit(3).toList();
        List<Tournament> recentTournaments = tournaments.stream().limit(4).toList();
        List<Team> bestTeams = teams.stream().limit(4).toList();

        model.addAttribute("matches", recentMatches);
        model.addAttribute("teams", bestTeams);
        model.addAttribute("tournaments", recentTournaments);
        model.addAttribute("name", "Home");
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("name", "Signup");
        return "UserRegistration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("name", "Login");
        return "Login";
    }

    @GetMapping("/players/create")
    public String createPlayer(Model model) {
        model.addAttribute("name", "Create Player");
        return "PlayerRegistration";
    }

    @GetMapping("/team/select")
    public String selectTeam(Model model) {
        model.addAttribute("name", "Select Team");
        return "CreateTeam";
    }
}
