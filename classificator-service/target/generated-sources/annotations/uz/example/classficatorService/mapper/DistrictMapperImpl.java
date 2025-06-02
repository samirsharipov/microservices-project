package uz.example.classficatorService.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.DistrictDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T10:48:07+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class DistrictMapperImpl implements DistrictMapper {

    @Override
    public DistrictDto toDto(District district) {
        if ( district == null ) {
            return null;
        }

        DistrictDto.DistrictDtoBuilder districtDto = DistrictDto.builder();

        districtDto.regionId( districtRegionId( district ) );
        districtDto.id( district.getId() );
        districtDto.name( district.getName() );

        return districtDto.build();
    }

    @Override
    public District toEntity(DistrictDto districtDto) {
        if ( districtDto == null ) {
            return null;
        }

        District.DistrictBuilder district = District.builder();

        district.region( mapRegionIdToRegion( districtDto.getRegionId() ) );
        district.id( districtDto.getId() );
        district.name( districtDto.getName() );

        return district.build();
    }

    @Override
    public List<DistrictDto> toDtoList(List<District> districts) {
        if ( districts == null ) {
            return null;
        }

        List<DistrictDto> list = new ArrayList<DistrictDto>( districts.size() );
        for ( District district : districts ) {
            list.add( toDto( district ) );
        }

        return list;
    }

    @Override
    public void update(District district, DistrictDto districtDto) {
        if ( districtDto == null ) {
            return;
        }

        district.setId( districtDto.getId() );
        district.setName( districtDto.getName() );
    }

    private Long districtRegionId(District district) {
        if ( district == null ) {
            return null;
        }
        Region region = district.getRegion();
        if ( region == null ) {
            return null;
        }
        Long id = region.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
