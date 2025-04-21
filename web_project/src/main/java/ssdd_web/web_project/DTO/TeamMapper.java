package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Team;
import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface TeamMapper {

    @Mapping(target = "player1.team", ignore = true)
    @Mapping(target = "player2.team", ignore = true)
    TeamDTO toDTO(Team team);

    List<TeamDTO> toDTOs(Collection<Team> teams);

    @Mapping(target = "id", ignore = true)
    Team toDomain(TeamDTO dto);

}
