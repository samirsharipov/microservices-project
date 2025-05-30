package uz.example.classficatorService.service;

import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.RegionDto;

public interface RegionService {

    ApiResponse createRegion(RegionDto regionDto);

    ApiResponse getAllRegions();

    ApiResponse getRegionById(Long id);

    ApiResponse updateRegion(Long id, RegionDto regionDto);

    ApiResponse deleteRegion(Long id);
}
