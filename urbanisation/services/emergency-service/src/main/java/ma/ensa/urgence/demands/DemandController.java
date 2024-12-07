package ma.ensa.urgence.demands;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/emergency/demands")
@RestController
public class DemandController {

    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @GetMapping
    public List<Demand> getDemands() {
        return demandService.getDemands();
    }

    @GetMapping("/{cin}")
    public List<Demand> getDemandsByCin(@PathVariable String cin) {
        return demandService.getDemandsByCin(cin);
    }
}
