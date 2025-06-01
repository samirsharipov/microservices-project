package uz.example.classficatorService.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.example.classficatorService.controller.RegionController;
import uz.example.classficatorService.helpers.ResponseEntityHelper;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.payload.RegionDto;
import uz.example.classficatorService.service.RegionService;


@RestController
@RequiredArgsConstructor
public class RegionControllerImpl implements RegionController {

    private final RegionService regionService;
    private final ResponseEntityHelper responseEntityHelper;

    @Override
    public ResponseEntity<ApiResponse> createRegion(RegionDto regionDto) {
        return responseEntityHelper.buildResponse(regionService.createRegion(regionDto));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllRegions() {
        return responseEntityHelper.buildResponse(regionService.getAllRegions());
    }

    @Override
    public ResponseEntity<ApiResponse> getRegionById(Long id) {
        return responseEntityHelper.buildResponse(regionService.getRegionById(id));
    }

    @Override
    public ResponseEntity<ApiResponse> updateRegion(Long id, RegionDto regionDto) {
        return responseEntityHelper.buildResponse(regionService.updateRegion(id, regionDto));
    }

    @Override
    public ResponseEntity<ApiResponse> deleteRegion(Long id) {
        return responseEntityHelper.buildResponse(regionService.deleteRegion(id));
    }
}
