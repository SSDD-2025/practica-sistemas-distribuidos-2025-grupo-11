package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Surface;
import ssdd_web.web_project.model.Player.Hand;;

public record PlayerDTO(
        Long id,
        String name,
        String surname,
        String citizenship,
        Integer height,
        Double weight,
        Integer age,
        Hand bestHand,
        Surface bestSurface,
        TeamDTO team,
        Integer winsPlayer,
        Integer lossesPlayer,
        Double winratePlayer,
        Integer aces,
        Double acespm,
        Integer doubleFaults,
        Double doubleFaultspm) {
}