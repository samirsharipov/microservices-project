package uz.samir.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.payload.ApiResponse;
import uz.samir.authservice.payload.LoginRequest;
import uz.samir.authservice.payload.RegisterRequest;


@RequestMapping("api/auth")
public interface AuthController {

    @PostMapping("/register")
    ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request);

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request);

    @GetMapping("/me")
    ResponseEntity<ApiResponse> getMe(@AuthenticationPrincipal User user);
}
