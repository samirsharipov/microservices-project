package uz.example.classficatorService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.DistrictDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    @Mapping(source = "region.id", target = "regionId")
    DistrictDto toDto(District district);

    @Mapping(source = "regionId", target = "region", qualifiedByName = "mapRegionIdToRegion")
    District toEntity(DistrictDto districtDto);

    List<DistrictDto> toDtoList(List<District> districts);

    @Named("mapRegionIdToRegion")
    default Region mapRegionIdToRegion(Long regionId) {
        if (regionId == null) {
            return null;
        }
        Region region = new Region();
        region.setId(regionId);
        return region;
    }

    @Mapping(target = "region", ignore = true)
    void update(@MappingTarget District district, DistrictDto districtDto);
}
