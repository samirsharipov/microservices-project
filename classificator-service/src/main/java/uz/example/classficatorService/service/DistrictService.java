package uz.example.classficatorService.service;

import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.DistrictDto;

public interface DistrictService {

    ApiResponse createDistrict(DistrictDto districtDto);

    ApiResponse getAllDistricts();

    ApiResponse getDistrictById(Long id);

    ApiResponse updateDistrict(Long id, DistrictDto districtDto);

    ApiResponse deleteDistrict(Long id);
}
