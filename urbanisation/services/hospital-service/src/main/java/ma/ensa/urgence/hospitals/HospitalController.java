package ma.ensa.urgence.hospitals;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/hospital")
@RestController
public class HospitalController {

    private final HospitalService hospitalService;
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    public Hospital getId(@PathVariable("id") int id) {
        return hospitalService.getHospitalById(id);
    }

    @GetMapping("/code/{code}")
    public List<Hospital> getByCode(@PathVariable("code") String code) {
        return hospitalService.getHospitalsByCode(code);
    }

    @GetMapping("/me")
    public Hospital getHospitalInfo() {
        int id = 1;
        return hospitalService.getHospitalById(id);
    }
}
