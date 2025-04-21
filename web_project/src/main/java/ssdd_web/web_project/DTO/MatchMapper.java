package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Match;

import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { TeamMapper.class })
public interface MatchMapper {

    @Mapping(target = "homeTeam", source = "homeTeam")
    @Mapping(target = "awayTeam", source = "awayTeam")
    MatchDTO toDTO(Match match);

    List<MatchDTO> toDTOs(Collection<Match> matches);

    @Mapping(target = "id", ignore = true)
    Match toDomain(MatchDTO matchDTO);
}
