package ssdd_web.web_project.services;

import java.io.InputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.repository.PlayerRepository;
import ssdd_web.web_project.repository.TeamRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Player savePlayer(Player player) {
        if (player.getName() == null || player.getName().isEmpty() || player.getSurname() == null ||
                player.getSurname().isEmpty()) {
            throw new IllegalArgumentException("Player name is required");
        }
        if (playerRepository.existsByName(player.getName()) && playerRepository.existsBySurname(player.getSurname())) {
            throw new IllegalArgumentException("Player name already exists");
        }

        return playerRepository.save(player);
    }

    // save edited player
    public Player saveEditPlayer(Player player) {
        Player existingPlayer = playerRepository.findById(player.getId()).orElse(null);

        if (existingPlayer != null) {
            if (player.getTeam() == null) {
                player.setTeam(existingPlayer.getTeam());
            }
        }
        return playerRepository.save(player);
    }

    // get player by id
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    // get all players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // delete player by id
    @Transactional
    public void deletePlayerById(Long Id) {
        Player player = playerRepository.findById(Id).orElseThrow(() -> new RuntimeException("Player not found"));

        if (player.getTeam() != null) {
            Team team = player.getTeam();
            if (team.getPlayer1() != null && team.getPlayer1().getId() == Id) {
                team.getPlayer2().setTeam(null);
                team.setPlayer1(null);
            } else if (team.getPlayer2() != null && team.getPlayer2().getId() == Id) {
                team.getPlayer1().setTeam(null);
                team.setPlayer2(null);
            }

            if (team.getPlayer1() == null || team.getPlayer2() == null) {
                teamRepository.delete(team);
            }
        }

        playerRepository.deleteById(Id);

    }

    public Optional<Player> findById(long id) {
        return playerRepository.findById(id);
    }

    public Resource getPlayerImage(long id) throws SQLException {
    Player player = playerRepository.findById(id).orElseThrow();
    if (player.getPlayerImage() != null) {
        return new InputStreamResource(player.getPlayerImage().getBinaryStream());
    } else {
        throw new NoSuchElementException("Imagen no encontrada");
    }
    }

    public void createPlayerImage(long id, InputStream inputStream, long size) {
        Player player = playerRepository.findById(id).orElseThrow();
        player.setPlayerImage(BlobProxy.generateProxy(inputStream, size));
        playerRepository.save(player);
    }

    public void deletePlayerImage(long id){
        Player player = playerRepository.findById(id).orElseThrow();
        if (player.getPlayerImage() == null) {
            throw new NoSuchElementException("Imagen no encontrada");
        }
        player.setPlayerImage(null);
        playerRepository.save(player);
    }
}

