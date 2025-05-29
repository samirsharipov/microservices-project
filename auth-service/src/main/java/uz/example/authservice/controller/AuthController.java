package uz.example.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.example.authservice.entity.User;
import uz.example.authservice.payload.ApiResponse;
import uz.example.authservice.payload.LoginRequest;
import uz.example.authservice.payload.RegisterRequest;


@RequestMapping("api/auth")
public interface AuthController {

    @PostMapping("/register")
    ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request);

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request);

    @GetMapping("/me")
    ResponseEntity<ApiResponse> getMe(@AuthenticationPrincipal User user);
}
