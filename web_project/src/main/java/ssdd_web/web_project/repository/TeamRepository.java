package ssdd_web.web_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByOrderByRankingAsc();
    //Optional<Team> findByPlayer1OrPlayer2(Player player1, Player player2);
}
