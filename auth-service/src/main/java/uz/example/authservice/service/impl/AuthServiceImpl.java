package uz.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.example.authservice.entity.Role;
import uz.example.authservice.entity.User;
import uz.example.authservice.mapper.UserMapper;
import uz.example.authservice.payload.ApiResponse;
import uz.example.authservice.payload.LoginRequest;
import uz.example.authservice.payload.RegisterRequest;
import uz.example.authservice.repository.UserRepository;
import uz.example.authservice.service.AuthService;
import uz.example.authservice.service.JwtService;

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
