package uz.example.classficatorService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import uz.example.classficatorService.payload.ApiResponse;

public interface InitController {

    @PostMapping("/init")
    ResponseEntity<ApiResponse> initData();
}
