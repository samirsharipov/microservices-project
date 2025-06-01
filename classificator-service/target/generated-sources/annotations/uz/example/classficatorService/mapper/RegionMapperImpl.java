package uz.example.classficatorService.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.DistrictDto;
import uz.example.classficatorService.payload.RegionDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T20:41:03+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class RegionMapperImpl implements RegionMapper {

    @Override
    public RegionDto toDto(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionDto.RegionDtoBuilder regionDto = RegionDto.builder();

        regionDto.countryId( regionCountryId( region ) );
        regionDto.id( region.getId() );
        regionDto.name( region.getName() );
        regionDto.districts( districtListToDistrictDtoList( region.getDistricts() ) );

        return regionDto.build();
    }

    @Override
    public Region toEntity(RegionDto regionDto) {
        if ( regionDto == null ) {
            return null;
        }

        Region.RegionBuilder region = Region.builder();

        region.country( mapCountryIdToCountry( regionDto.getCountryId() ) );
        region.id( regionDto.getId() );
        region.name( regionDto.getName() );

        return region.build();
    }

    @Override
    public List<RegionDto> toDtoList(List<Region> regions) {
        if ( regions == null ) {
            return null;
        }

        List<RegionDto> list = new ArrayList<RegionDto>( regions.size() );
        for ( Region region : regions ) {
            list.add( toDto( region ) );
        }

        return list;
    }

    @Override
    public void update(Region region, RegionDto regionDto) {
        if ( regionDto == null ) {
            return;
        }

        region.setId( regionDto.getId() );
        region.setName( regionDto.getName() );
    }

    private Long regionCountryId(Region region) {
        if ( region == null ) {
            return null;
        }
        Country country = region.getCountry();
        if ( country == null ) {
            return null;
        }
        Long id = country.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected DistrictDto districtToDistrictDto(District district) {
        if ( district == null ) {
            return null;
        }

        DistrictDto.DistrictDtoBuilder districtDto = DistrictDto.builder();

        districtDto.id( district.getId() );
        districtDto.name( district.getName() );

        return districtDto.build();
    }

    protected List<DistrictDto> districtListToDistrictDtoList(List<District> list) {
        if ( list == null ) {
            return null;
        }

        List<DistrictDto> list1 = new ArrayList<DistrictDto>( list.size() );
        for ( District district : list ) {
            list1.add( districtToDistrictDto( district ) );
        }

        return list1;
    }
}
