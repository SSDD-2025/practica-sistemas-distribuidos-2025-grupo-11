package ssdd_web.web_project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.repository.PlayerRepository;
import ssdd_web.web_project.repository.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    // all players list
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // all teams list
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team createTeam(Long player1Id, Long player2Id) {
        Optional<Player> player1 = playerRepository.findById(player1Id);
        Optional<Player> player2 = playerRepository.findById(player2Id);

        if (player1.isPresent() && player2.isPresent()) {
            Team team = new Team(player1.get(), player2.get());
            return teamRepository.save(team);
        }
        throw new RuntimeException("Jugador no encontrado");
    }

    // get team by id
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    // delete team by id
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
