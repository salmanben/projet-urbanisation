package ma.ensa.urgence.teams;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensa.urgence.demands.DemandService;
import ma.ensa.urgence.demands.ValidatedDemandResponse;
import ma.ensa.urgence.hospitals.AssignHospitalRequest;
import ma.ensa.urgence.hospitals.HospitalResponse;

@RestController
@RequestMapping("/api/emergency/teams")
public class TeamController {

    private final TeamService teamService;
    private final DemandService demandService;

    public TeamController(TeamService teamService, DemandService demandService) {
        this.teamService = teamService;
        this.demandService = demandService;
    }

    @GetMapping("/{demandId}")
    public TeamResponse getTeam(@PathVariable int demandId) {
        return teamService.getTeam(demandId);
    }

    @GetMapping("/{id}/demands")
    public List<ValidatedDemandResponse> getTeamDemands(@PathVariable int id) {
        return demandService.getTeamDemands(id);
    }

    @PostMapping("/valid-assignment/{id}")
    public void validAssignment(@PathVariable int id) {
        teamService.validAssignment(id);
    }

    // assign hospital
    @PostMapping("/assign-hospital")
    public HospitalResponse assignHospital(@RequestBody AssignHospitalRequest assignHospitalRequest) {
        System.out.println("\n\nassignHospital Code: " + assignHospitalRequest.getCode()  + "\n\n");
        return teamService.assignHospital(assignHospitalRequest);
    }
    
}
