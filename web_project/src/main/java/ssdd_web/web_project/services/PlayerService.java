package ssdd_web.web_project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.repository.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    // save player in database
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // get player by id
    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }
}
