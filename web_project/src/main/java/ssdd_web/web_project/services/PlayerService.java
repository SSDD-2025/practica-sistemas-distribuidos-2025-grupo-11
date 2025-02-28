package ssdd_web.web_project.services;

import java.util.List;

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
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    //get all players
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    //delete player by id
    public void deletePlayerById(Long Id) {
        playerRepository.deleteById(Id);
    }
}
