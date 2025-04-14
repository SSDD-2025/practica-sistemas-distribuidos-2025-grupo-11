package ssdd_web.web_project.DTO;

public record PlayerDTO(
        Long id,    // necessary for team controller
        String name,
        String surname,
        String citizenship,
        double height,
        double weight,
        int age,
        String bestHand,
        String bestSurface) {
}