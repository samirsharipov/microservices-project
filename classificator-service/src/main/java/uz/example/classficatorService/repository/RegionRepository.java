package uz.example.classficatorService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.classficatorService.entity.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByCountryId(Long countryId);
    Optional<Region> findByNameAndCountryId(String name, Long countryId);
}
