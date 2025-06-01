package uz.example.classficatorService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.mapper.CountryMapper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.CountryDto;
import uz.example.classficatorService.repository.CountryRepository;
import uz.example.classficatorService.service.CountryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public ApiResponse createCountry(CountryDto countryDto) {
        countryRepository.save(countryMapper.toEntity(countryDto));
        return new ApiResponse("Country created successfully", true);
    }

    @Override
    public ApiResponse getAllCountries() {
        return new ApiResponse("Countries retrieved successfully", true,
                countryMapper.toDtoList(countryRepository.findAll()));
    }

    @Override
    public ApiResponse getCountryById(Long id) {
        return countryRepository.findById(id)
                .map(country -> new ApiResponse("Country found", true,
                        countryMapper.toDto(country)))
                .orElseGet(() -> new ApiResponse("Country not found", false));
    }

    @Override
    public ApiResponse updateCountry(Long id, CountryDto countryDto) {
        Optional<Country> optional = countryRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("Country not found", false);
        }
        Country country = optional.get();
        countryMapper.update(country, countryDto);
        countryRepository.save(country);
        return new ApiResponse("Country updated successfully", true);
    }

    @Override
    public ApiResponse deleteCountry(Long id) {
        Optional<Country> optional = countryRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("Country not found", false);
        }
        countryRepository.deleteById(id);
        return new ApiResponse("Country deleted successfully", true);
    }
}
