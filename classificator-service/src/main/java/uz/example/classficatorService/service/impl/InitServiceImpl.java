package uz.example.classficatorService.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.example.classficatorService.entity.Country;
import uz.example.classficatorService.entity.District;
import uz.example.classficatorService.entity.Region;
import uz.example.classficatorService.payload.ApiResponse;
import uz.example.classficatorService.repository.CountryRepository;
import uz.example.classficatorService.repository.DistrictRepository;
import uz.example.classficatorService.repository.RegionRepository;
import uz.example.classficatorService.service.InitService;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitServiceImpl implements InitService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final ObjectMapper objectMapper;

    // agar servis ishga tushganda avtomatik ravishda ma'lumotlarni yuklash kerak bo'lsa ushbu method ni commentdan chiqarish kerak
    // @PostConstruct
    // public void initDataOnStartup() {
    //    loadInitialData();
    // }

    @Transactional
    @Override
    public ApiResponse initData() {
        // faqat database ma'lumotlar bolmasa yani bo'sh bolsa ushbu method ishlaydi faqat boshlangich malumotlarni yuklash uchun
        if (countryRepository.count() > 0) {
            log.info("Ma'lumotlar bazasida allaqachon ma'lumotlar mavjud. Yuklash bekor qilindi.");
            return new ApiResponse("Ma'lumotlar bazasida allaqachon ma'lumotlar mavjud. Yuklash bekor qilindi.", false);
        }

        try {
            InputStream inputStream = new ClassPathResource("data.json").getInputStream();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            for (JsonNode countryNode : rootNode.get("countries")) {
                Country country = Country.builder()
                        .name(countryNode.get("name").asText())
                        .code(countryNode.get("code").asText())
                        .build();
                country = countryRepository.save(country);

                if (countryNode.has("regions")) {
                    for (JsonNode regionNode : countryNode.get("regions")) {
                        Region region = Region.builder()
                                .name(regionNode.get("name").asText())
                                .country(country)
                                .build();
                        region = regionRepository.save(region);

                        if (regionNode.has("districts")) {
                            for (JsonNode districtNode : regionNode.get("districts")) {
                                District district = District.builder()
                                        .name(districtNode.get("name").asText())
                                        .region(region)
                                        .build();
                                districtRepository.save(district);
                            }
                        }
                    }
                }
            }
            log.info("Boshlang'ich ma'lumotlar muvaffaqiyatli yuklandi.");
            return new ApiResponse("Boshlang'ich ma'lumotlar muvaffaqiyatli yuklandi.", true);
        } catch (IOException e) {
            log.error("Boshlang'ich ma'lumotlarni yuklashda xato yuz berdi: {}", e.getMessage());
            return new ApiResponse("Boshlang'ich ma'lumotlarni yuklashda xato yuz berdi: " + e.getMessage(), false);
        }
    }
}
