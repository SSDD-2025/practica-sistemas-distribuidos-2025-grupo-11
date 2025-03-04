package ssdd_web.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd_web.web_project.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}
