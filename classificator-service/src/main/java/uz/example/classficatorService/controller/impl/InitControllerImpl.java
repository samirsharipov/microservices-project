package uz.example.classficatorService.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.example.classficatorService.controller.InitController;
import uz.example.classficatorService.helpers.ResponseEntityHelper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.service.InitService;

@RestController
@RequiredArgsConstructor
public class InitControllerImpl implements InitController {

    private final InitService initService;
    private final ResponseEntityHelper responseEntityHelper;

    @Override
    public ResponseEntity<ApiResponse> initData() {
        return responseEntityHelper.buildResponse(initService.initData());
    }
}
