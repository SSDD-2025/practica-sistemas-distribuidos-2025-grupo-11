package ssdd_web.web_project.controller.web;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    // create tournament
    @GetMapping("/register")
    public String showTournCreateForm(Model model) {
        model.addAttribute("matches", tournamentService.getAllMatches());
        return "CreateTournament"; // html where user picks tournament data
    }

    // add tournament to database
    @PostMapping("/add")
    public String createTournament(@RequestParam String name, @RequestParam LocalDate dateT,
            @RequestParam int givenPoints, @RequestParam String location, @RequestParam double prizeMoney,
            @RequestParam Surface surface, @RequestParam Long match1Id, @RequestParam Long match2Id,
            @RequestParam Long match3Id, @RequestParam Long match4Id) {

        List<Long> matchIds = Arrays.asList(match1Id, match2Id, match3Id, match4Id);
        tournamentService.createTournament(name, dateT, givenPoints, prizeMoney, location, surface, matchIds);

        return "redirect:/home";
    }

    // all tournaments list
    @GetMapping("/list")
    public String listTournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.getAllTournaments());
        return "TournamentsList"; // html with all tournaments
    }

    // team stats show
    @GetMapping("/{id}")
    public String showTournamentDetails(@PathVariable Long id, Model model) {
        Tournament tournament = tournamentService.getTournamentById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        model.addAttribute("tournament", tournament);
        model.addAttribute("matches", tournament.getMatches());
        return "TournamentDetails";
    }

    // delete team by id
    @PostMapping("/delete/{id}")
    public String deleteTeamById(@PathVariable Long id) {
        tournamentService.deleteTournamentById(id);
        return "redirect:/tournaments/list";
    }
}
