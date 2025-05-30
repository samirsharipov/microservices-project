package uz.example.classficatorService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.example.classficatorService.entity.District;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByRegionId(Long regionId);
    Optional<District> findByNameAndRegionId(String name, Long regionId);
}
