package ssdd_web.web_project.controller.web;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.model.Team;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.TeamService;
import ssdd_web.web_project.services.UserService;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/register")
    public String showSignupForm() {
        return "UserRegistration";
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

    @GetMapping("/list")
    public String getUsersList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "UsersList1";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isAdmin = userService.isCurrentUserAdmin();

        if (!isAdmin && !currentUsername.equals(user.getName())) {
            throw new AccessDeniedException("You are not authorized to delete this account");
        }
        if (user.getTeam() != null) {
            Team team = user.getTeam();
            user.setTeam(null);
            teamService.deleteTeamById(team.getId());
        }
        userService.deleteUserbyId(user.getId());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}