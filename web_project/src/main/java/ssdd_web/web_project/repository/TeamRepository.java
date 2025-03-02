package ssdd_web.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd_web.web_project.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByOrderByRankingAsc();

    List<Team> findByAvailableTrue();
}
