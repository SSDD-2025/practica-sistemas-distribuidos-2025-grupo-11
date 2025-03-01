package ssdd_web.web_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.services.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // new player
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("player", new Player());
        return "playerRegistration"; // "PlayerRegistration.html"
    }

    // save player in database
    @PostMapping("/add")
    public String savePlayerDatabase(@ModelAttribute Player player) {
        playerService.savePlayer(player);
        return "redirect:/ValidRegistration.html";
    }

    @GetMapping("/list")
    public String listPlayers(Model model, @RequestParam(required = false) Player player) {
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "PlayerList";
    }

    // show player
    @GetMapping("/{id}")
    public String getPlayerStats(@PathVariable Long id, Model model) {
        Player player = playerService.getPlayerById(id);
        model.addAttribute("player", player);
        return "Player";
    }

    // delete player by id
    @PostMapping("/delete/{id}")
    public String deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return "redirect:/players/list";
    }

    // edit player
    @GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable Long id, Model model) {
        Player player = playerService.getPlayerById(id);
        model.addAttribute("player", player);
        return "PlayerEditForm";
    }

    // update player
    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute Player player) {
        playerService.savePlayer(player);
        return "redirect:/players/" + player.getId();
    }

}