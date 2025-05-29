package uz.samir.authservice.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.samir.authservice.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password; // todo  password encode ni ko'rgach olib tashla
    private Role role;
}
