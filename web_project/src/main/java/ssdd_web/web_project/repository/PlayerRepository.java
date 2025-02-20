package ssdd_web.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ssdd_web.web_project.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    
}
