package fr.volkan.back.user.mapper;

import fr.volkan.back.user.dto.UserResponse;
import fr.volkan.back.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
}
