package uz.example.classficatorService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.RegionDto;

@RequestMapping("/regions")
public interface RegionController {

    @PostMapping
    ResponseEntity<ApiResponse> createRegion(@RequestBody RegionDto regionDto);

    @GetMapping
    ResponseEntity<ApiResponse> getAllRegions();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getRegionById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateRegion(@PathVariable Long id, @RequestBody RegionDto regionDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteRegion(@PathVariable Long id);
}
