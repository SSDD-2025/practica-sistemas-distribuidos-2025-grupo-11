package ssdd_web.web_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.services.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // new player
    @GetMapping("/players/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("player", new Player());
        return "PlayerRegistration"; // Thymeleaf buscara "PlayerRegistration.html"
    }

    // save player in database
    @PostMapping("/players/add")
    public String postMethodName(@ModelAttribute Player player) {
        playerService.savePlayer(player);
        return "redirect:/players/" + player.getId() + "/stats";
    }

}
