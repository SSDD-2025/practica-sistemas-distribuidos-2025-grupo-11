package ssdd_web.web_project.DTO;

import java.util.List;

public record UserDTO(
                Long id,
                String name,
                String email,
                List<String> roles,
                Double winrate,
                Integer winsUser,
                Integer lossesUser) {
}
