package ssdd_web.web_project.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.PlayerRepository;
import ssdd_web.web_project.repository.TeamRepository;
import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.PlayerMapper;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerMapper playerMapper;

    public PlayerDTO savePlayer(PlayerDTO playerDTO) {
        if (playerDTO.name() == null || playerDTO.name().isEmpty() || playerDTO.surname() == null ||
                playerDTO.surname().isEmpty()) {
            throw new IllegalArgumentException("Player name is required");
        }
        if (playerRepository.existsByName(playerDTO.name()) && playerRepository.existsBySurname(playerDTO.surname())) {
            throw new IllegalArgumentException("Player name already exists");
        }

        Player player = playerMapper.toDomain(playerDTO);
        return playerMapper.toDTO(playerRepository.save(player));
    }

    // save edited player
    public PlayerDTO saveEditPlayer(PlayerDTO playerDTO) {
        Player existingPlayer = playerRepository.findById(playerDTO.id()).orElse(null);
        if (existingPlayer == null) {
            throw new RuntimeException("Player not found");
        }

        Player player = playerMapper.toDomain(playerDTO);
        player.setId(existingPlayer.getId());

        if (player.getTeam() == null) {
            player.setTeam(existingPlayer.getTeam());
        }

        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(savedPlayer);
    }

    // save edited player with image
    public PlayerDTO saveEditPlayerImage(PlayerDTO playerDTO, MultipartFile imageFile) throws IOException {
        Player existingPlayer = playerRepository.findById(playerDTO.id())
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Player player = playerMapper.toDomain(playerDTO);
        player.setId(existingPlayer.getId());

        if (imageFile == null || imageFile.isEmpty()) {
            player.setPlayerImage(existingPlayer.getPlayerImage());
        } else {
            player.setPlayerImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        }

        if (player.getTeam() == null) {
            player.setTeam(existingPlayer.getTeam());
        }

        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(savedPlayer);
    }

    // get player by id
    public PlayerDTO getPlayerById(Long id) {
        return playerMapper.toDTO(playerRepository.findById(id).orElse(null));
    }

    // get all players
    public List<PlayerDTO> getAllPlayers() {
        return playerMapper.toDTOs(playerRepository.findAll());
    }

    // delete player by id
    @Transactional
    public void deletePlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        if (player.getTeam() != null) {
            Team team = player.getTeam();
            if (team.getPlayer1() != null && team.getPlayer1().getId() == id) {
                team.getPlayer2().setTeam(null);
                team.setPlayer1(null);
            } else if (team.getPlayer2() != null && team.getPlayer2().getId() == id) {
                team.getPlayer1().setTeam(null);
                team.setPlayer2(null);
            }

            if (team.getPlayer1() == null || team.getPlayer2() == null) {
                User manager = team.getManager();
                team.setManager(null);
                manager.setTeam(null);
                teamRepository.delete(team);
            }
        }

        playerRepository.deleteById(id);

    }

    public Optional<PlayerDTO> findById(long id) {
        return playerRepository.findById(id).map(playerMapper::toDTO);
    }

    public Resource getPlayerImage(long id) throws SQLException {
        Player player = playerRepository.findById(id).orElseThrow();
        if (player.getPlayerImage() != null) {
            return new InputStreamResource(player.getPlayerImage().getBinaryStream());
        } else {
            throw new NoSuchElementException("Not found");
        }
    }

    public void uploadPlayerImage(long id, InputStream inputStream, long size) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        if (player.getPlayerImage() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The player already has an image");
        }

        player.setPlayerImage(BlobProxy.generateProxy(inputStream, size));
        playerRepository.save(player);
    }

    // Permite crear o reemplazar
    public void updatePlayerImage(long id, InputStream inputStream, long size) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found"));

        player.setPlayerImage(BlobProxy.generateProxy(inputStream, size));
        playerRepository.save(player);
    }

    public void deletePlayerImage(long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        if (player.getPlayerImage() == null) {
            throw new NoSuchElementException("Not found");
        }
        player.setPlayerImage(null);
        playerRepository.save(player);
    }

    public Page<PlayerDTO> getAllPlayersPaged(Pageable pageable) {
        return playerRepository.findAll(pageable).map(playerMapper::toDTO);
    }
}
