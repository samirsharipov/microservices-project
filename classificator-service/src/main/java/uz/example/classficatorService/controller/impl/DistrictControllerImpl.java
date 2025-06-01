package uz.example.classficatorService.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.example.classficatorService.controller.DistrictController;
import uz.example.classficatorService.helpers.ResponseEntityHelper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.DistrictDto;
import uz.example.classficatorService.service.DistrictService;


@RestController
@RequiredArgsConstructor
public class DistrictControllerImpl implements DistrictController {

    private final DistrictService districtService;
    private final ResponseEntityHelper responseEntityHelper;

    @Override
    public ResponseEntity<ApiResponse> createDistrict(DistrictDto districtDto) {
        return responseEntityHelper.buildResponse(districtService.createDistrict(districtDto));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllDistricts() {
        return responseEntityHelper.buildResponse(districtService.getAllDistricts());
    }

    @Override
    public ResponseEntity<ApiResponse> getDistrictById(Long id) {
        return responseEntityHelper.buildResponse(districtService.getDistrictById(id));
    }

    @Override
    public ResponseEntity<ApiResponse> updateDistrict(Long id, DistrictDto districtDto) {
        return responseEntityHelper.buildResponse(districtService.updateDistrict(id, districtDto));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteDistrict(Long id) {
        return responseEntityHelper.buildResponse(districtService.deleteDistrict(id));
    }
}
