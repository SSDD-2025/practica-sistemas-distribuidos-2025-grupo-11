package ssdd_web.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ssdd_web.web_project.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
    
}
