package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Player;
import java.util.List;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "team", ignore = true)
    PlayerDTO toDTO(Player player);

    List<PlayerDTO> toDTOs(Collection<Player> players);

    @Mapping(target = "id", ignore = true)
    Player toDomain(PlayerDTO dto);
}
