package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Tournament;
import java.util.List;
import java.util.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    TournamentDTO toDTO(Tournament tournament);

    List<TournamentDTO> toDTOs(Collection<Tournament> tournaments);

    Tournament toEntity(TournamentDTO tournamentDTO);

}
