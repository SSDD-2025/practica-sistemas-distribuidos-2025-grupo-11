package ssdd_web.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssdd_web.web_project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
