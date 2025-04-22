package ssdd_web.web_project.controller.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ssdd_web.web_project.services.PlayerService;
import ssdd_web.web_project.DTO.PlayerDTO;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Get all players
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    // get player by id
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        PlayerDTO playerDTO = playerService.getPlayerById(id);
        if (playerDTO != null) {
            return ResponseEntity.ok(playerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO savedPlayer = playerService.savePlayer(playerDTO);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    // Update existing player
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDTO) {
        PlayerDTO updatedPlayerDTO = playerService.saveEditPlayer(playerDTO);
        return ResponseEntity.ok(updatedPlayerDTO);
    }

    // delete a player
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

    // Images
    // Get the image of a player
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getPlayerImage(@PathVariable long id) throws SQLException {
        Resource image = playerService.getPlayerImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(image);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> createPlayerImage(@PathVariable long id,
            @RequestParam MultipartFile imageFile) throws IOException {
        playerService.uploadPlayerImage(id, imageFile.getInputStream(), imageFile.getSize());
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deletePlayerImage(@PathVariable long id) throws IOException {
        playerService.deletePlayerImage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<Object> replacePlayerImage(@PathVariable long id,
            @RequestParam MultipartFile imageFile) throws IOException {
        playerService.updatePlayerImage(id, imageFile.getInputStream(), imageFile.getSize());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<PlayerDTO>> getPlayersPaged(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<PlayerDTO> players = playerService.getAllPlayersPaged(pageable);
        return ResponseEntity.ok(players);
    }
}
