package ssdd_web.web_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // save user to database
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
