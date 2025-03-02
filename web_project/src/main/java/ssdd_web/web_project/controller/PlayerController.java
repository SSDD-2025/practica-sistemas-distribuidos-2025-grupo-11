package ssdd_web.web_project.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

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
    public String savePlayerDatabase(@ModelAttribute Player player, MultipartFile imageFile)
            throws IOException, SQLException {
        if (!imageFile.isEmpty()) {
            player.setPlayerImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        }
        playerService.savePlayer(player); // Save player to database
        return "redirect:/home";
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

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Player> player = playerService.findById(id);
        if (player.isPresent() && player.get().getPlayerImage() != null) {
            Blob image = player.get().getPlayerImage();
            Resource file = new InputStreamResource(image.getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(image.length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}