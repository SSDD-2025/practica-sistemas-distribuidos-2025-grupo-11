package ssdd_web.web_project.controller.web;

import java.io.IOException;
import java.security.Principal;
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

import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.DTO.PlayerDTO;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    // new player
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("player",
                new PlayerDTO(null, null, null, null, 0, 0.0, 0, null, null, null, 0, 0, 0.0, 0, 0.0, 0, 0.0));
        return "playerRegistration"; // "PlayerRegistration.html"
    }

    // save player in database
    @PostMapping("/add")
    public String savePlayerDatabase(@ModelAttribute PlayerDTO playerDTO, MultipartFile imageFile)
            throws IOException, SQLException {
        if (!imageFile.isEmpty()) {

        }
        try {
            playerService.savePlayer(playerDTO); // Save player to database
            return "redirect:/home";
        } catch (IllegalArgumentException ex) {
            return "redirect:errorPlayer"; // Redirect to the page with the error message
        }
    }

    // show all players
    @GetMapping("/list")
    public String listPlayers(Model model) {
        List<PlayerDTO> playersDTO = playerService.getAllPlayers();
        model.addAttribute("players", playersDTO);
        return "PlayerList";
    }

    // show player
    @GetMapping("/{id}")
    public String getPlayerStats(@PathVariable Long id, Model model) {
        PlayerDTO playerDTO = playerService.getPlayerById(id);
        model.addAttribute("player", playerDTO);
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
        PlayerDTO playerDTO = playerService.getPlayerById(id);
        model.addAttribute("player", playerDTO);
        return "PlayerEditForm";
    }

    // update player
    @PostMapping("/update")
    public String updatePlayer(@ModelAttribute PlayerDTO playerDTO) {
        playerService.saveEditPlayer(playerDTO);
        return "redirect:/players/" + playerDTO.id();
    }

    /*
     * @GetMapping("/{id}/image")
     * public ResponseEntity<Object> downloadImage(@PathVariable long id) throws
     * SQLException {
     * Optional<Player> player = playerService.findById(id);
     * if (player.isPresent() && player.get().getPlayerImage() != null) {
     * Blob image = player.get().getPlayerImage();
     * Resource file = new InputStreamResource(image.getBinaryStream());
     * return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
     * .contentLength(image.length()).body(file);
     * } else {
     * return ResponseEntity.notFound().build();
     * }
     * }
     */

}