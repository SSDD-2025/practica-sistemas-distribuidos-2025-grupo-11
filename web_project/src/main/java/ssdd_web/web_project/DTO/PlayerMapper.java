package ssdd_web.web_project.DTO;

import ssdd_web.web_project.DTO.PlayerDTO;
import ssdd_web.web_project.model.Player;
import ssdd_web.web_project.model.Surface;

import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDTO toDTO(Player player);

    List<PlayerDTO> toDTOList(Collection<Player> players);

    @Mapping(target = "id", ignore = true)
    Player toEntity(PlayerDTO dto);
}