package uz.samir.authservice.service;

import org.springframework.http.ResponseEntity;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.payload.ApiResponse;
import uz.samir.authservice.payload.LoginRequest;
import uz.samir.authservice.payload.RegisterRequest;

import java.nio.file.attribute.UserPrincipal;

public interface AuthService {

    ApiResponse register(RegisterRequest request);

    ApiResponse login(LoginRequest request);

    ApiResponse getMe(User user);
}
