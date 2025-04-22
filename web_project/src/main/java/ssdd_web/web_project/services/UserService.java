package ssdd_web.web_project.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.TeamRepository;
import ssdd_web.web_project.repository.UserRepository;
import ssdd_web.web_project.DTO.UserDTO;
import ssdd_web.web_project.DTO.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    // save user to database
    public User saveUser(User user) {
        if (userRepository.findByName(user.getName()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public void deleteUserbyId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user.getTeam() != null) {
            user.setTeam(null);
        }
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public UserDTO getLoggedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return mapper.toDTO(userRepository.findByName(username).get());
    }

    public Optional<User> getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByName(username);
    }
}
