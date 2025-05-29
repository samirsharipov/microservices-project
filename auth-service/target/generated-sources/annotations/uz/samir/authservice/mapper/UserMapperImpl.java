package uz.samir.authservice.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.payload.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T17:53:15+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setRole( user.getRole() );

        return userDto;
    }
}
