package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Match;

import java.util.List;
import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface MatchMapper {
    MatchDTO toDto(Match match);

    List<MatchDTO> toDtoList (Collection<Match> matches);

    @Mapping(target = "id", ignore = true)
    Match toEntity(MatchDTO matchDTO);
}
