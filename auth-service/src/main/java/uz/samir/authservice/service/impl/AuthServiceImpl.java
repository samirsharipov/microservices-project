package uz.samir.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.samir.authservice.entity.Role;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.mapper.UserMapper;
import uz.samir.authservice.payload.ApiResponse;
import uz.samir.authservice.payload.LoginRequest;
import uz.samir.authservice.payload.RegisterRequest;
import uz.samir.authservice.repository.UserRepository;
import uz.samir.authservice.service.AuthService;
import uz.samir.authservice.service.JwtService;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public ApiResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new ApiResponse("Username already exists", false);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new ApiResponse("Successfully registered", true, jwtToken);
    }

    @Override
    public ApiResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(user);
            return new ApiResponse("Successfully logged in", true, token);

        } catch (Exception e) {
            return new ApiResponse("Invalid username or password", false);
        }
    }

    @Override
    public ApiResponse getMe(User user) {
        return new ApiResponse("Successfully", true, userMapper.toUserDto(user));
    }
}
