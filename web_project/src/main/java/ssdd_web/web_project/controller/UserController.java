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
import org.springframework.web.multipart.MultipartFile;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;

    // Save in the database
    @PostMapping("/register")
    public String register(@ModelAttribute User user, MultipartFile userFile) throws SQLException, IOException {
        user.setRoles(java.util.List.of("USER"));
        userService.saveUser(user);
        return "redirect:/login";
    }

    // Show the user
    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "UserProfile";
    } 
}
