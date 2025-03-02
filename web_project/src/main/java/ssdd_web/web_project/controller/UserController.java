package ssdd_web.web_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
