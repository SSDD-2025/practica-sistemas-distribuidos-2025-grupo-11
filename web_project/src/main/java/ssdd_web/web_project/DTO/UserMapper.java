package ssdd_web.web_project.DTO;

import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import ssdd_web.web_project.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(Collection<User> users);

    User toDomain(UserDTO userDTO);

}
