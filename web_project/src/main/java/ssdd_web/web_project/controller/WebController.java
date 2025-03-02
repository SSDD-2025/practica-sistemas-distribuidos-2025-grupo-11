package ssdd_web.web_project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/home")
    public String webString(Model model) {
        model.addAttribute("name", "Home");
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("name", "Signup");
        return "UserRegistration";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("name", "Login");
        return "Login";
    }

    @GetMapping("/players/create")
    public String createPlayer(Model model) {
        model.addAttribute("name", "Create Player");
        return "PlayerRegistration";
    }

    @GetMapping("/team/select")
    public String selectTeam(Model model) {
        model.addAttribute("name", "Select Team");
        return "CreateTeam";
    }
}
