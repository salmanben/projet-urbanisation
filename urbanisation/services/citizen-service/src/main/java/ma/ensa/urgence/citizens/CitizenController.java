package ma.ensa.urgence.citizens;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.urgence.categories.CategoryResponse;
import ma.ensa.urgence.categories.CategoryService;
import ma.ensa.urgence.demands.DemandRequest;
import ma.ensa.urgence.demands.DemandResponse;
import ma.ensa.urgence.demands.DemandService;
import ma.ensa.urgence.kafka.CitizenProducer;

@RequestMapping("/api/citizen")
@RestController
public class CitizenController {

    private final CitizenService citizenService;
    private final CategoryService categoryService;
    private final DemandService demandeService;
    private final CitizenProducer citizenProducer;

    public CitizenController(CitizenService citizenService, CategoryService categoryService,
            DemandService demandeService, CitizenProducer citizenProducer) {
        this.citizenService = citizenService;
        this.categoryService = categoryService;
        this.demandeService = demandeService;
        this.citizenProducer = citizenProducer;
    }

    @GetMapping("/{id}")
    public Citizen get(@PathVariable int id) {
        return citizenService.getCitizenById(id);
    }

    @GetMapping("/cin/{cin}")
    public Citizen get(@PathVariable String cin) {
        return citizenService.getCitizenByCIN(cin);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/send-demand")
    public void addDemand(@RequestBody DemandRequest demandRequest) {
        System.out.println("\n\nDemandRequest: " + demandRequest + "\n\n");
        citizenProducer.sendCitizenDemand(demandRequest);
    }

    @PostMapping("/get-demands/{cin}")
    public List<DemandResponse> getDemands(@PathVariable String cin) {
        return demandeService.getDemands(cin);
    }
}
