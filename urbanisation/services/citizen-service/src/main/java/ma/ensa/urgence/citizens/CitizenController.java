package ma.ensa.urgence.citizens;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/citizen")
@RestController
public class CitizenController {

    private final CitizenService citizenService;


    @GetMapping("/{id}")
    public Citizen get(@PathVariable int id) {
        return citizenService.getCitizenById(id);
    }

    @GetMapping("cin/{cin}")
    public Citizen get(@PathVariable String cin) {
        return citizenService.getCitizenByCIN(cin);
    }
}
