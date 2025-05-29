package uz.example.authservice.service;

import uz.example.authservice.entity.User;
import uz.example.authservice.payload.ApiResponse;
import uz.example.authservice.payload.LoginRequest;
import uz.example.authservice.payload.RegisterRequest;

public interface AuthService {

    ApiResponse register(RegisterRequest request);

    ApiResponse login(LoginRequest request);

    ApiResponse getMe(User user);
}
