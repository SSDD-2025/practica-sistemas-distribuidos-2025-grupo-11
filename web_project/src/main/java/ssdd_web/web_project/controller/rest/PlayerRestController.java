package ssdd_web.web_project.controller.rest;

import java.io.IOException;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.services.PlayerService;
import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.PlayerMapper;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerRestController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    // Obtener todos los jugadores
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerMapper.toDTOList(playerService.getAllPlayers());
        return ResponseEntity.ok(players);
    }

    // Obtener un jugador por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(playerMapper.toDTO(player));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);
        Player savedPlayer = playerService.savePlayer(player);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPlayer.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(playerMapper.toDTO(savedPlayer));
    }

    // Actualizar un jugador existente
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player existingPlayer = playerService.getPlayerById(id);
        if (existingPlayer == null) {
            return ResponseEntity.notFound().build();
        }

        Player updatedPlayer = playerMapper.toEntity(playerDTO);
        updatedPlayer.setId(id); // Asegurar que mantiene el mismo ID
        playerService.saveEditPlayer(updatedPlayer);

        return ResponseEntity.ok(playerMapper.toDTO(updatedPlayer));
    }

    // Eliminar un jugador por ID
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
    public ResponseEntity<Page<PlayerDTO>> getPlayersPaged(Pageable pageable) {
        Page<Player> players = playerService.getAllPlayersPaged(pageable);
        return ResponseEntity.ok(players.map(playerMapper::toDTO));
    }
}
