package ssdd_web.web_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ssdd_web.web_project.model.User;

@Controller
public class UserController {
    @PostMapping("/user/register")
    public String register(@ModelAttribute User user) {
        return "redirect:/login";
    }
}
