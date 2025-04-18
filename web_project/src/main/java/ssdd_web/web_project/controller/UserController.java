package ssdd_web.web_project.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;

    // new user
    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "UserRegistration"; // busca signup.html en templates
    }

    // save user in database
    @PostMapping("/add")
    public String saveUserDatabase(@RequestParam String email, @RequestParam String name,
            @RequestParam String password) {
        User user = new User(email, name, password, "ROLE_USER");
        userService.saveUser(user);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("name", "Login");
        return "login";
    }

    // Show the user
    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "UserProfile";
    }
}
