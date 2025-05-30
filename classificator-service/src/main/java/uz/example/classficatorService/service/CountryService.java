package uz.example.classficatorService.service;

import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.CountryDto;

public interface CountryService {

    ApiResponse createCountry(CountryDto countryDto);

    ApiResponse getAllCountries();

    ApiResponse getCountryById(Long id);

    ApiResponse updateCountry(Long id, CountryDto countryDto);

    ApiResponse deleteCountry(Long id);
}
