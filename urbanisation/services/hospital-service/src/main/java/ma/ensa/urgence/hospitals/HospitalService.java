package ma.ensa.urgence.hospitals;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class HospitalService {

    private final HospitalDao hospitalDao;
    private final CodeDao codeDao;
    private final RestTemplate restTemplate;

    @Value("${spring.application.services.emergency-service.url}")
    private String emergencyServiceUrl;


    public HospitalService(HospitalDao hospitalDao, CodeDao codeDao, RestTemplate restTemplate) {
        this.hospitalDao = hospitalDao;
        this.codeDao = codeDao;
        this.restTemplate = restTemplate;
    }

    public Hospital getHospitalById(int id) {
        return hospitalDao.findById(id).orElse(null);
    }

    public Hospital getHospitalByUserId(int userId) {
        return hospitalDao.findByUserId(userId);
    }

    public List<Hospital> getHospitalsByCode(String code) {
        Code codeEntity = codeDao.findByCode(code);
        if(codeEntity == null) {
            return null;
        }
        return codeEntity.getHospitals();
    }

    public List<CodeDto> getCodes() {
        return codeDao.findAll().stream()
                .map(code -> new CodeDto(code.getId(), code.getCode(), code.getDescription()))
                .toList();
    }

    public List<Object> getDemands(int id) {
        Hospital hospital =  hospitalDao.findByUserId(id);
        System.out.println("\n\n" + emergencyServiceUrl + "/hospitals/"+ hospital.getId() + "/demands");
        List<Object> demands = restTemplate.getForObject(emergencyServiceUrl + "/hospitals/"+ hospital.getId() + "/demands", List.class);

        return demands;
    }
}
