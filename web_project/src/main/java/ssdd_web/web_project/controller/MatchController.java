package ssdd_web.web_project.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ssdd_web.web_project.model.Match;
import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.services.MatchService;
import ssdd_web.web_project.services.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/createMatch")
    public String showMatchCreate(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "CreateMatch"; // html where we create a match
    }

    @PostMapping("/createMatch")
    public String postMethodName(@RequestParam Long homeTeamID, @RequestParam Long awayTeamId,
            @RequestParam LocalDate dateM, @RequestParam Surface surface) {

        Match match = matchService.createMatch(homeTeamID, awayTeamId, dateM, surface);
        return "redirect:/matches/" + match.getId();
    }

    // all matches
    @GetMapping
    public String listMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        return "ListMatches"; // html with all matches
    }

    // match details
    @GetMapping("/{id}")
    public String showMatchDetails(@PathVariable Long id, Model model) {
        Match match = matchService.getMatchById(id).orElseThrow(() -> new RuntimeException("Partido no encontrado"));
        return "MatchDetails"; // html with match details
    }

}
