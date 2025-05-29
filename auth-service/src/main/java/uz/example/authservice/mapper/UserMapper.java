package uz.example.authservice.mapper;

import org.mapstruct.Mapper;
import uz.example.authservice.entity.User;
import uz.example.authservice.payload.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
