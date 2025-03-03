package ssdd_web.web_project.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        // Add the error message to the model
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorPlayer";  // This will resolve to the error.html page
    }
}