package ssdd_web.web_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.services.PlayerService;


@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // new player
    @GetMapping("/players/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("player", new Player());
        return "playerRegistration"; // "PlayerRegistration.html"
    }

    // save player in database
    @PostMapping("/players/add")
    public String savePlayerDatabase(@ModelAttribute Player player) {
        playerService.savePlayer(player);
        return "redirect:/ValidRegistration.html";
    }

    @GetMapping("/players/list")
    public String getAllPlayers(Model model) {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "PlayerList";
    }
    

}
