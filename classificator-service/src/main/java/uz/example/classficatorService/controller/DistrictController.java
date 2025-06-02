package uz.example.classficatorService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.DistrictDto;

@RequestMapping("/districts")
public interface DistrictController {

    @PostMapping
    ResponseEntity<ApiResponse> createDistrict(@RequestBody DistrictDto districtDto);

    @GetMapping
    ResponseEntity<ApiResponse> getAllDistricts();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getDistrictById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateDistrict(@PathVariable Long id, @RequestBody DistrictDto districtDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteDistrict(@PathVariable Long id);
}
