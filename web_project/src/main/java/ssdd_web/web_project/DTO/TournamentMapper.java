package ssdd_web.web_project.DTO;

import ssdd_web.web_project.model.Tournament;
import java.util.List;
import java.util.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface TournamentMapper {

    TournamentDTO toDto (Tournament tournament);

    List<TournamentDTO> toDtoList (Collection<Tournament> tournaments);
    Tournament toEntity (TournamentDTO tournamentDTO);
    
}
