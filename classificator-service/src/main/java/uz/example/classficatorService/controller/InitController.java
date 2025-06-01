package uz.example.classficatorService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.example.classficatorService.payload.ApiResponse;

@RequestMapping("/api/classificator")
public interface InitController {

    @PostMapping("/init")
    ResponseEntity<ApiResponse> initData();
}
