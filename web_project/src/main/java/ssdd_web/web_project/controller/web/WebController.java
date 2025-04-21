package ssdd_web.web_project.controller.web;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.MatchService;
import ssdd_web.web_project.services.TeamService;
import ssdd_web.web_project.services.TournamentService;
import ssdd_web.web_project.services.UserService;

@Controller
public class WebController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private UserService userService;

    @ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if(principal != null) {
		
			model.addAttribute("logged", true);		
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
		} else {
			model.addAttribute("logged", false);
		}
	}
    // get home
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

    // Show the user
    @GetMapping("/profile")
    public String getUserInfo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = userService.getLoggedUser();
        model.addAttribute("user", user);

        return "UserProfile";
    }
}
