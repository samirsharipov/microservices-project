package uz.samir.authservice.controller.iml;

import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.samir.authservice.controller.AuthController;
import uz.samir.authservice.entity.User;
import uz.samir.authservice.helpers.ResponseEntityHelper;
import uz.samir.authservice.payload.ApiResponse;
import uz.samir.authservice.payload.LoginRequest;
import uz.samir.authservice.payload.RegisterRequest;
import uz.samir.authservice.service.AuthService;

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
