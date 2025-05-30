package uz.example.classficatorService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.RegionDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DistrictMapper.class}) // DistrictMapper'dan foydalanish
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    @Mapping(source = "country.id", target = "countryId")
    RegionDto toDto(Region region);

    @Mapping(source = "countryId", target = "country", qualifiedByName = "mapCountryIdToCountry")
    @Mapping(target = "districts", ignore = true) // Yangilashda districtlarni e'tiborsiz qoldirish
    Region toEntity(RegionDto regionDto);

    List<RegionDto> toDtoList(List<Region> regions);

    @Named("mapCountryIdToCountry")
    default Country mapCountryIdToCountry(Long countryId) {
        if (countryId == null) {
            return null;
        }
        Country country = new Country();
        country.setId(countryId);
        return country;
    }
}

