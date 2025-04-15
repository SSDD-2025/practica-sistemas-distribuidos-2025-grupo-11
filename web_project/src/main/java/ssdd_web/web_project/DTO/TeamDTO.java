package ssdd_web.web_project.DTO;

public record TeamDTO (
        Long id,
        String name,
        PlayerDTO player1,
        PlayerDTO player2
) {
    
}
