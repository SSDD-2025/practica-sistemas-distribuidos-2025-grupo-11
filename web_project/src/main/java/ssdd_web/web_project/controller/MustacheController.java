package ssdd_web.web_project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {
    @GetMapping("/")
    public String mustache(Model model) {
        model.addAttribute("name", "Home");
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("name", "Signup");
        return "UserRegistration";
    }

}
