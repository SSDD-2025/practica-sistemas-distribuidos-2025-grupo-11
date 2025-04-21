package ssdd_web.web_project.DTO;

public record TeamDTO(
                Long id,
                String name,
                PlayerDTO player1,
                PlayerDTO player2,
                UserDTO manager,
                Integer winsTeam,
                Integer lossesTeam,
                Double winrateTeam,
                Boolean available,
                Integer ranking,
                Integer points) {
}
