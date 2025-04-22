package ssdd_web.web_project.controller.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.repository.UserRepository;
import ssdd_web.web_project.services.TeamService;
import ssdd_web.web_project.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TeamService teamService;

    @GetMapping("/register")
    public String showSignupForm() {
        return "UserRegistration"; // busca signup.html en templates
    }

    // Save in the database
    @PostMapping("/add")
    public String registerUser(@RequestParam("name") String name, @RequestParam("password") String password,
            @RequestParam("email") String email) {

        User user = new User(email, name, password);
        user.setRoles(List.of("USER"));

        userService.saveUser(user);

        return "redirect:/users/login";
    }

    @PostMapping("/delete")
    public String deleteUser() {
        User user = userService.getUser().orElse(null);
        user.setTeam(null);
        userService.deleteUserbyId(user.getId());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}