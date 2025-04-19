package ssdd_web.web_project.DTO;

import org.mapstruct.Mapper;
import ssdd_web.web_project.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

}
