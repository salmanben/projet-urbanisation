package ma.ensa.urgence.hospitals;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.urgence.demands.HospitalDemand;

@RestController
@RequestMapping("api/emergency/hospitals")
public class HospitalController {

    private final HospitalsAssignmentService hospitalAssignmentService;
    public HospitalController(HospitalsAssignmentService hospitalAssignmentService) {
        this.hospitalAssignmentService = hospitalAssignmentService;
    }

    @GetMapping("/{id}/demands")
    public List<HospitalDemand> getHospitalDemands(@PathVariable int id) {
        return hospitalAssignmentService.getHospitalDemands(id);
    }

    
}
