package uz.example.classficatorService.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.mapper.DistrictMapper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.DistrictDto;
import uz.example.classficatorService.repository.DistrictRepository;
import uz.example.classficatorService.repository.RegionRepository;
import uz.example.classficatorService.service.DistrictService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final RegionRepository regionRepository;

    @Override
    public ApiResponse createDistrict(DistrictDto districtDto) {
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (optionalRegion.isEmpty()) {
            return new ApiResponse("Region not found",false);
        }

        districtRepository.save(districtMapper.toEntity(districtDto));
        return new ApiResponse("District created successfully", true);
    }

    @Override
    public ApiResponse getAllDistricts() {
        return new ApiResponse("Districts retrieved successfully", true,
                districtMapper.toDtoList(districtRepository.findAll()));
    }

    @Override
    public ApiResponse getDistrictById(Long id) {
        return districtRepository.findById(id)
                .map(district -> new ApiResponse("District found", true, districtMapper.toDto(district)))
                .orElseGet(() -> new ApiResponse("District not found", false));
    }

    @Override
    public ApiResponse updateDistrict(Long id, DistrictDto districtDto) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("District not found", false);
        }
        Optional<Region> optionalRegion = regionRepository.findById(districtDto.getRegionId());
        if (optionalRegion.isEmpty()) {
            return new ApiResponse("Region not found", false);
        }

        District district = optional.get();
        districtMapper.update(district, districtDto);
        district.setRegion(optionalRegion.get());
        districtRepository.save(district);
        return new ApiResponse("District updated successfully", true);
    }

    @Override
    public ApiResponse deleteDistrict(Long id) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isEmpty()) {
            return new ApiResponse("District not found", false);
        }
        districtRepository.deleteById(id);
        return new ApiResponse("District deleted successfully", true);
    }
}
