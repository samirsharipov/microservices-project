package uz.example.classficatorService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.DistrictDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {
    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

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
}

