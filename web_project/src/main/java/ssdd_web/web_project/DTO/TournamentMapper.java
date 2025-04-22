package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Tournament;
import java.util.List;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MatchMapper.class })
public interface TournamentMapper {

    TournamentDTO toDTO(Tournament tournament);

    List<TournamentDTO> toDTOs(Collection<Tournament> tournaments);

    @Mapping(target = "id", ignore = true)
    Tournament toDomain(TournamentDTO tournamentDTO);

}
