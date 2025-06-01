package uz.example.classficatorService.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.CountryDto;
import uz.example.classficatorService.payload.DistrictDto;
import uz.example.classficatorService.payload.RegionDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T20:41:03+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryDto toDto(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryDto.CountryDtoBuilder countryDto = CountryDto.builder();

        countryDto.id( country.getId() );
        countryDto.name( country.getName() );
        countryDto.code( country.getCode() );
        countryDto.regions( regionListToRegionDtoList( country.getRegions() ) );

        return countryDto.build();
    }

    @Override
    public Country toEntity(CountryDto countryDto) {
        if ( countryDto == null ) {
            return null;
        }

        Country.CountryBuilder country = Country.builder();

        country.id( countryDto.getId() );
        country.name( countryDto.getName() );
        country.code( countryDto.getCode() );

        return country.build();
    }

    @Override
    public List<CountryDto> toDtoList(List<Country> countries) {
        if ( countries == null ) {
            return null;
        }

        List<CountryDto> list = new ArrayList<CountryDto>( countries.size() );
        for ( Country country : countries ) {
            list.add( toDto( country ) );
        }

        return list;
    }

    @Override
    public void update(Country country, CountryDto countryDto) {
        if ( countryDto == null ) {
            return;
        }

        country.setId( countryDto.getId() );
        country.setName( countryDto.getName() );
        country.setCode( countryDto.getCode() );
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

    protected RegionDto regionToRegionDto(Region region) {
        if ( region == null ) {
            return null;
        }

        RegionDto.RegionDtoBuilder regionDto = RegionDto.builder();

        regionDto.id( region.getId() );
        regionDto.name( region.getName() );
        regionDto.districts( districtListToDistrictDtoList( region.getDistricts() ) );

        return regionDto.build();
    }

    protected List<RegionDto> regionListToRegionDtoList(List<Region> list) {
        if ( list == null ) {
            return null;
        }

        List<RegionDto> list1 = new ArrayList<RegionDto>( list.size() );
        for ( Region region : list ) {
            list1.add( regionToRegionDto( region ) );
        }

        return list1;
    }
}
