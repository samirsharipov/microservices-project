package uz.example.classficatorService.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.payload.CountryDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RegionMapper.class}) // RegionMapper'dan foydalanish
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryDto toDto(Country country);

    @Mapping(target = "regions", ignore = true) // Yangilashda regionlarni e'tiborsiz qoldirish
    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countries);
}
