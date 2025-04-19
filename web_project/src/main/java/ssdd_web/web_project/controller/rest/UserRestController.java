package ssdd_web.web_project.controller.rest;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import ssdd_web.web_project.DTO.UserDTO;
import ssdd_web.web_project.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

}
