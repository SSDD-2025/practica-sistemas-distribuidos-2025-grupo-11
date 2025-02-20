package ssdd_web.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ssdd_web.web_project.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    
}
