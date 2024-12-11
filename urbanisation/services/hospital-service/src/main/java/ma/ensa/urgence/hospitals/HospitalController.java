package ma.ensa.urgence.hospitals;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.Jwt;

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

    @GetMapping("/codes")
    public List<CodeDto> getCodes() {
        return hospitalService.getCodes();
    }

    @GetMapping("/me")
    public Hospital getHospitalInfo(@AuthenticationPrincipal Jwt jwt) {
        Long idLong = jwt.getClaim("id");
        int id = idLong.intValue();

        return hospitalService.getHospitalByUserId(id);
    }

    @GetMapping("/demands")
    public List<Object> getHospitalDemands(@AuthenticationPrincipal Jwt jwt) {
        Long idLong = jwt.getClaim("id");
        int id = idLong.intValue();

        System.out.println("\n\nUser ID: " + id);
        return hospitalService.getDemands(id);
    }

    @PostMapping("/handle-demand")
    public void HandleDemand(@RequestBody HandleDemandRequest handleDemandRequest){
        hospitalService.handleDemand(handleDemandRequest);
    }
}
