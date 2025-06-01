package uz.example.classficatorService.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.payload.CountryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto toDto(Country country);

    @Mapping(target = "regions", ignore = true)
    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countries);

    @Mapping(target = "regions", ignore = true)
    void update(@MappingTarget Country country, CountryDto countryDto);
}
