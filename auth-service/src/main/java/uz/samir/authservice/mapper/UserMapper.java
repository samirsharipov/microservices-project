package uz.samir.authservice.mapper;

import org.mapstruct.Mapper;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.payload.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
