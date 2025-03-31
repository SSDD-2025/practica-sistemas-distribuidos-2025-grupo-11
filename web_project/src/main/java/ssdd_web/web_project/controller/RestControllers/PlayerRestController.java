package ssdd_web.web_project.controller.RestControllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.services.PlayerService;
import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.PlayerMapper;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    private final PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Obtener todos los jugadores
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = PlayerMapper.toDTOList(playerService.getAllPlayers());
        return ResponseEntity.ok(players);
    }

    // Obtener un jugador por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(PlayerMapper.toDTO(player));
        }
        return ResponseEntity.notFound().build();
    }

    // Crear un nuevo jugador
    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = PlayerMapper.toEntity(playerDTO);
        Player savedPlayer = playerService.savePlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(PlayerMapper.toDTO(savedPlayer));
    }

    // Actualizar un jugador existente
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player existingPlayer = playerService.getPlayerById(id);
        if (existingPlayer == null) {
            return ResponseEntity.notFound().build();
        }

        Player updatedPlayer = PlayerMapper.toEntity(playerDTO);
        updatedPlayer.setId(id); // Asegurar que mantiene el mismo ID
        playerService.saveEditPlayer(updatedPlayer);

        return ResponseEntity.ok(PlayerMapper.toDTO(updatedPlayer));
    }

    // Eliminar un jugador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }

    // Subir imagen de un jugador
    /*@PostMapping("/{id}/image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile imageFile)
            throws IOException, SQLException {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        player.setPlayerImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        playerService.saveEditPlayer(player);

        return ResponseEntity.ok("Imagen subida correctamente");
    }*/

    // Descargar imagen de un jugador
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Player> player = playerService.findById(id);
        if (player.isPresent() && player.get().getPlayerImage() != null) {
            Blob image = player.get().getPlayerImage();
            Resource file = new InputStreamResource(image.getBinaryStream());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(image.length())
                    .body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
