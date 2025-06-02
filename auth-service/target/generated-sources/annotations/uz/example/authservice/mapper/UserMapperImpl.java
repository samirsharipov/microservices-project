package uz.example.authservice.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.example.authservice.entity.User;
import uz.example.authservice.payload.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T09:32:24+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
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
