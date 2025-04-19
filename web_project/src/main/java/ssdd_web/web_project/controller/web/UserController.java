package ssdd_web.web_project.controller.web;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import ssdd_web.web_project.repository.UserRepository;
import ssdd_web.web_project.services.UserService;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "UserRegistration"; // busca signup.html en templates
    }

    // Save in the database
    @PostMapping("/add")
    public String registerUser(@RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email) {

        // Verificar si el usuario ya existe
        if (userRepository.findByName(name).isPresent()) {
            return "redirect:/register?error=exists";
        }

        User user = new User();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password)); // Codificar contraseña aquí
        user.setEmail(email);
        user.setRoles(List.of("USER")); // Puedes ajustarlo según tu modelo

        // TODO: guardar la imagen si lo deseas
        // if (userFile != null && !userFile.isEmpty()) { ... }

        userRepository.save(user);

        return "redirect:/login?registered";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("name", "Login");
        return "login";
    }

    // Show the user
    @GetMapping("/profile")
    public String getUserInfo(Model model) {
        model.addAttribute("user", userService.getLoggedUser());
        return "UserProfile";
    }
}