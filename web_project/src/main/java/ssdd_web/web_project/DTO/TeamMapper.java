package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Team;
import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface TeamMapper {
    TeamDTO toDTO (Team team);
    List<TeamDTO> toDTOList (Collection<Team> teams);

    @Mapping(target = "id", ignore = true)
    Team toEntity (TeamDTO dto);
    
}
