package ssdd_web.web_project.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.PlayerRepository;
import ssdd_web.web_project.repository.TeamRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.AccessDeniedException;

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

    // all available players
    public List<Player> getAvailablePlayers() {
        return playerRepository.findByTeamIsNull();
    }

    // all teams order by ranking
    public List<Team> getAllTeamsByRanking() {
        return teamRepository.findByOrderByRankingAsc();
    }

    // all teams list
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // all available teams for tournament
    public List<Team> getAllAvailableTeams() {
        return teamRepository.findByAvailableTrue();
    }

    public Team createTeam(String name, Long player1Id, Long player2Id, User manager) {
        Optional<Player> player1 = playerRepository.findById(player1Id);
        Optional<Player> player2 = playerRepository.findById(player2Id);

        if (player1.isPresent() && player2.isPresent() && player1Id != player2Id) {
            Team team = new Team(name, player1.get(), player2.get(), manager);
            player1.get().setTeam(team);
            player2.get().setTeam(team);
            return teamRepository.save(team);
        }
        throw new RuntimeException("Player not found");
    }

    public Team createTeam(String name, Long player1Id, Long player2Id) {
        Optional<Player> player1 = playerRepository.findById(player1Id);
        Optional<Player> player2 = playerRepository.findById(player2Id);

        if (player1.isPresent() && player2.isPresent() && player1Id != player2Id) {
            Team team = new Team(name, player1.get(), player2.get());
            player1.get().setTeam(team);
            player2.get().setTeam(team);
            return teamRepository.save(team);
        }
        throw new RuntimeException("Player not found");
    }

    // get team by id
    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    // delete team by id
    public void deleteTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            Team existTeam = team.get();
    
            String currentUsername = getCurrentUsername();
    
            // ⚠️ Control de dueño
            if (!existTeam.getManager().getName().equals(currentUsername) &&
                !isCurrentUserAdmin()) {
                throw new AccessDeniedException("No tienes permiso para borrar este equipo");
            }
    
            if (existTeam.getPlayer1() != null) {
                existTeam.getPlayer1().setTeam(null);
                playerRepository.save(existTeam.getPlayer1());
            }
            if (existTeam.getPlayer2() != null) {
                existTeam.getPlayer2().setTeam(null);
                playerRepository.save(existTeam.getPlayer2());
            }
    
            existTeam.setManager(null);
            teamRepository.deleteById(id);
        } else {
            throw new RuntimeException("Team not found");
        }
    }

    public Page<Team> getAllTeamsPaged(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    
    private boolean isCurrentUserAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
            .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
    }
}
