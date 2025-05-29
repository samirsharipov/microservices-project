package uz.example.authservice.controller.iml;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.example.authservice.controller.AuthController;
import uz.example.authservice.entity.User;
import uz.example.authservice.helpers.ResponseEntityHelper;
import uz.example.authservice.payload.ApiResponse;
import uz.example.authservice.payload.LoginRequest;
import uz.example.authservice.payload.RegisterRequest;
import uz.example.authservice.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;
    private final ResponseEntityHelper responseEntityHelper;

    @Override
    public ResponseEntity<ApiResponse> register(RegisterRequest request) {
        return responseEntityHelper.buildResponse(authService.register(request));
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginRequest request) {
        return responseEntityHelper.buildResponse(authService.login(request));
    }

    @Override
    public ResponseEntity<ApiResponse> getMe( User user) {
        return responseEntityHelper.buildResponse(authService.getMe(user));
    }
}
