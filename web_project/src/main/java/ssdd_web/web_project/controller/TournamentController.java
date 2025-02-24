package ssdd_web.web_project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Tournament;
import ssdd_web.web_project.services.TournamentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    // create tournament
    @GetMapping("/tournCreate")
    public String showTournCreateForm(Model model) {
        model.addAttribute("teams", tournamentService.getAllTeams());
        return "CreateTournament"; // html where user picks tournament data
    }

    @PostMapping("/tournCreate")
    public String createTournament(@RequestBody String date, @RequestParam int givenPoints,
            @RequestParam String location, @RequestParam double prizeMoney, @RequestParam Surface surface,
            @RequestParam List<Long> teamIds) {
        LocalDate dateT = LocalDate.parse(date);
        Tournament tournament = tournamentService.createTournament(dateT, givenPoints, location, prizeMoney, surface,
                teamIds);

        return "redirect:/tournaments/" + tournament.getId();
    }

    // all tournaments list
    @GetMapping
    public String listTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "ListTournaments"; // html with all tournaments
    }

    // tournament details
    @GetMapping("/{id}")
    public String showTournamentDetails(@PathVariable Long id, Model model) {
        Optional<Tournament> tournament = tournamentService.getTournamentById(id);
        model.addAttribute("tournament", tournament);
        return "TournamentDetails"; // html with tournament details
    }

}
