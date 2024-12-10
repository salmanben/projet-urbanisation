package ma.ensa.urgence.teams;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.urgence.demands.DemandRequest;
import ma.ensa.urgence.demands.DemandResponse;
import ma.ensa.urgence.hospitals.AssignHospitalRequest;

import java.util.List;

@RequestMapping("/api/team")
@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("")
    public List<Team> getAll() {
        return teamService.getTeams();
    }

    @GetMapping("/demands")
    public List<Object> getAllDemands() {
        return teamService.getDemands();
    }


    @GetMapping("/me")
    public Team get() {
        int id = 3;
        return teamService.getTeamById(id);
    }


    @GetMapping("/{id}")
    public Team getTeam(@PathVariable("id") int id) {
        return teamService.getTeamById(id);
    }
    @PostMapping("/assign-team")
    public Team assignTeam(@RequestBody DemandRequest demand) {
        return teamService.assignTeam(demand);
    }

    @PostMapping("/valid-assignment/{id}")
    public void validDemand(@PathVariable("id") int id) {
        teamService.validDemand(id);
    }
    @GetMapping("/codes")
    public Object getCodes() {
        System.out.println("\n\n getCodes\n\n");
        return teamService.getCodes();
    }

    @PostMapping("/assign-hospital")
    public Object assignHospital(@RequestBody AssignHospitalRequest assignHospital) {
        return teamService.assignHospital(assignHospital);
    }


}
