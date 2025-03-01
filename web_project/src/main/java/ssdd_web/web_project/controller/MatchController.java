package ssdd_web.web_project.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.services.MatchService;
import ssdd_web.web_project.services.TeamService;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/register")
    public String showMatchCreate(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "CreateMatch"; // html where we create a match
    }

    @PostMapping("/add")
    public String createMatch(@RequestParam Long homeTeamId, @RequestParam Long awayTeamId,
            @RequestParam LocalDate dateM, @RequestParam Surface surface) {
        matchService.createMatch(homeTeamId, awayTeamId, dateM, surface);
        return "redirect:/home";
    }

    // all matches
    @GetMapping("/list")
    public String listMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatchesDateOrder());
        return "MatchesList"; // html with all matches
    }

    // match details
    @GetMapping("/{id}")
    public String showMatchDetails(@PathVariable Long id, Model model) {
        Match match = matchService.getMatchById(id).orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        model.addAttribute("match", match);
        return "MatchDetails"; // html with match details
    }

    // delete match by id
    @PostMapping("/delete/{id}")
    public String deleteMatchById(@PathVariable Long id) {
        matchService.deleteMatchById(id);

        return "redirect:/matches/list";
    }

}
