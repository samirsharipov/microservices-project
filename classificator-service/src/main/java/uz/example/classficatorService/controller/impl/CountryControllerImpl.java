package uz.example.classficatorService.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.example.classficatorService.controller.CountryController;
import uz.example.classficatorService.helpers.ResponseEntityHelper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.CountryDto;
import uz.example.classficatorService.service.CountryService;

@RestController
@RequiredArgsConstructor
public class CountryControllerImpl implements CountryController {

    private final CountryService countryService;
    private final ResponseEntityHelper responseEntityHelper;


    @Override
    public ResponseEntity<ApiResponse> createCountry(CountryDto countryDto) {
        return responseEntityHelper.buildResponse(countryService.createCountry(countryDto));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllCountries() {
        return responseEntityHelper.buildResponse(countryService.getAllCountries());
    }

    @Override
    public ResponseEntity<ApiResponse> getCountryById(Long id) {
        return responseEntityHelper.buildResponse(countryService.getCountryById(id));
    }

    @Override
    public ResponseEntity<ApiResponse> updateCountry(Long id, CountryDto countryDto) {
        return responseEntityHelper.buildResponse(countryService.updateCountry(id, countryDto));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCountry(Long id) {
        return responseEntityHelper.buildResponse(countryService.deleteCountry(id));
    }
}
