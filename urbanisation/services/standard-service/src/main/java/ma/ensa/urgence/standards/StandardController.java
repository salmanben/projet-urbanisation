package ma.ensa.urgence.standards;

import ma.ensa.urgence.categories.CategoryResponse;
import ma.ensa.urgence.categories.CategoryService;
import ma.ensa.urgence.citizens.CitizenService;
import ma.ensa.urgence.demands.DemandResponse;
import ma.ensa.urgence.demands.DemandService;
import ma.ensa.urgence.teams.TeamResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/standard")
public class StandardController {

    private final DemandService demandService;
    private final CitizenService citizenService;
    private final CategoryService categoryService;
    private final StandardService standardService;

    public StandardController(DemandService demandService, CitizenService citizenService,
            StandardService standardService,
            CategoryService categoryService) {
        this.demandService = demandService;
        this.citizenService = citizenService;
        this.categoryService = categoryService;
        this.standardService = standardService;
    }

    @GetMapping("/demands")
    public List<DemandStandard> getDemands() {
        List<DemandResponse> demandResponse = demandService.getDemands();
        List<DemandStandard> demandStandards = demandResponse.stream().map(demand -> {
            DemandStandard demandStandard = new DemandStandard();
            demandStandard.setId(demand.getId());
            demandStandard.setRef(demand.getRef());
            demandStandard.setCreatedAt(demand.getCreatedAt());
            demandStandard.setLatitude(demand.getLatitude());
            demandStandard.setLongitude(demand.getLongitude());
            demandStandard.setSeverityLevel(demand.getSeverityLevel());
            demandStandard.setStatus(demand.getStatus());
            CategoryResponse categoryResponse = categoryService.getCategory(demand.getCategoryId());
            demandStandard.setCategory(categoryResponse);
            demandStandard.setCitizen(citizenService.getCitizen(demand.getCin()));
            return demandStandard;
        }).collect(Collectors.toList());
        return demandStandards;
    }

    @PostMapping("/demands/{id}")
    public TeamResponse handleDemand(@PathVariable int id) {
        return standardService.handleDemand(id);
    }
}
