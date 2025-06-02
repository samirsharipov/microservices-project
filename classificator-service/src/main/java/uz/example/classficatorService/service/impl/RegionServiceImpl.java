package uz.example.classficatorService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.mapper.RegionMapper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.RegionDto;
import uz.example.classficatorService.repository.CountryRepository;
import uz.example.classficatorService.repository.RegionRepository;
import uz.example.classficatorService.service.RegionService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;
    private final RegionMapper regionMapper;

    @Override
    public ApiResponse createRegion(RegionDto regionDto) {
        Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());
        if (optionalCountry.isEmpty()) {
            return new ApiResponse("Country not found", false);
        }

        Region region = regionMapper.toEntity(regionDto);
        regionRepository.save(region);
        return new ApiResponse("Region created successfully", true);
    }

    @Override
    public ApiResponse getAllRegions() {
        return new ApiResponse("Regions retrieved successfully", true,
                regionMapper.toDtoList(regionRepository.findAll()));
    }

    @Override
    public ApiResponse getRegionById(Long id) {
        return regionRepository.findById(id)
                .map(region -> new ApiResponse("Region found", true, regionMapper.toDto(region)))
                .orElseGet(() -> new ApiResponse("Region not found", false));
    }

    @Override
    public ApiResponse updateRegion(Long id, RegionDto regionDto) {
        Optional<Region> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("Region not found", false);
        }
        Optional<Country> optionalCountry = countryRepository.findById(regionDto.getCountryId());
        if (optionalCountry.isEmpty()) {
            return new ApiResponse("Country not found", false);
        }

        Region region = optional.get();
        regionMapper.update(region, regionDto);
        region.setCountry(optionalCountry.get());
        regionRepository.save(region);
        return new ApiResponse("Region updated successfully", true);
    }

    @Override
    public ApiResponse deleteRegion(Long id) {
        Optional<Region> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("Region not found", false);
        }
        regionRepository.deleteById(id);
        return new ApiResponse("Region deleted successfully", true);
    }
}
