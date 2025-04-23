package ssdd_web.web_project.services;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.UserRepository;

@Service
public class DatabaseInitializer {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    DatabaseInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // @PostConstruct
    public void init() throws IOException, URISyntaxException {
        userRepository.save(new User("admin2", "admin2",
                passwordEncoder.encode("admin2"), "USER", "ADMIN"));
    }

}
