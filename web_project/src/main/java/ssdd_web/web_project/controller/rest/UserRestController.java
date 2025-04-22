package ssdd_web.web_project.controller.rest;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.DTO.UserDTO;
import ssdd_web.web_project.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDTO me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            return userService.getUser(principal.getName());
        } else {
            throw new NoSuchElementException();
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<UserDTO>> getUsersPaged(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<UserDTO> users = userService.getAllUsersPaged(pageable);
        return ResponseEntity.ok(users);
    }
}
