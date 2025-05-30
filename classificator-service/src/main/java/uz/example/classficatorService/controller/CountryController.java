package uz.example.classficatorService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.example.classficatorService.payload.CountryDto;
import uz.example.classficatorService.payload.ApiResponse;

@RequestMapping("/api/countries")
public interface CountryController {

    @PostMapping
    ResponseEntity<ApiResponse> createCountry(@RequestBody CountryDto countryDto);

    @GetMapping
    ResponseEntity<ApiResponse> getAllCountries();

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getCountryById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteCountry(@PathVariable Long id);
}
