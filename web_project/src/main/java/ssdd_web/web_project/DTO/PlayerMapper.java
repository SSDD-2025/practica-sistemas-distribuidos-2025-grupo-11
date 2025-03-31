package ssdd_web.web_project.DTO;

import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Surface;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    // Convertir Player a PlayerDTO
    public static PlayerDTO toDTO(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getSurname(),
                player.getCitizenship(),
                player.getHeight(),
                player.getWeight(),
                player.getAge(),
                player.getBestHand().name(),
                player.getBestSurface().name(),
                player.getWinsPlayer(),
                player.getLossesPlayer(),
                player.getWinratePlayer());
    }

    // Convertir PlayerDTO a Player
    public static Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setSurname(playerDTO.getSurname());
        player.setCitizenship(playerDTO.getCitizenship());
        player.setHeight(playerDTO.getHeight());
        player.setWeight(playerDTO.getWeight());
        player.setAge(playerDTO.getAge());
        player.setWinsPlayer(playerDTO.getWinsPlayer());
        player.setLossesPlayer(playerDTO.getLossesPlayer());
        player.setWinratePlayer(playerDTO.getWinratePlayer());

        try {
            player.setBestHand(Player.Hand.valueOf(playerDTO.getBestHand()));
            player.setBestSurface(Surface.valueOf(playerDTO.getBestSurface())); // Ahora usa Surface directamente
        } catch (IllegalArgumentException e) {
            player.setBestHand(null);
            player.setBestSurface(null);
        }
        return player;
    }

    // Convertir lista de Players a lista de PlayerDTOs
    public static List<PlayerDTO> toDTOList(List<Player> players) {
        return players.stream().map(PlayerMapper::toDTO).collect(Collectors.toList());
    }
}