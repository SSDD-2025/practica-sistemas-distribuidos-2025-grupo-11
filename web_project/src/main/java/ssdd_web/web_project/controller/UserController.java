package ssdd_web.web_project.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.User;
import ssdd_web.web_project.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;

    //Save in the database
    @PostMapping("/register")
    public String register(@ModelAttribute User user, MultipartFile userFile) throws SQLException, IOException {
        if (!userFile.isEmpty()) {
            user.setProfilePicture(BlobProxy.generateProxy(userFile.getInputStream(), userFile.getSize()));
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    //Show the user
    @GetMapping("/{id}")
    public String getUserInfo(@PathVariable Long id, Model model) {
       User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "User";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent() && user.get().getProfilePicture() != null) {
            Blob image = user.get().getProfilePicture();
            Resource file = new InputStreamResource(image.getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(image.length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
