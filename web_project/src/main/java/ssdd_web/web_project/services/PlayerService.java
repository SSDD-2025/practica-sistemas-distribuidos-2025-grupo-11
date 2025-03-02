package ssdd_web.web_project.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
