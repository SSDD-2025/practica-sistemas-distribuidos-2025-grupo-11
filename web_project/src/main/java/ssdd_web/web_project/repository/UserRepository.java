package ssdd_web.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd_web.web_project.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);    
}
