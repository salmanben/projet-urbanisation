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
import ma.ensa.urgence.hospitals.HospitalsAssignment;
import ma.ensa.urgence.hospitals.HospitalsAssignmentService;

@RestController
@RequestMapping("/api/emergency/teams")
public class TeamController {

    private final TeamService teamService;
    private final DemandService demandService;
    private final HospitalsAssignmentService hospitalsAssigmentService;

    public TeamController(TeamService teamService, DemandService demandService,
            HospitalsAssignmentService hospitalsAssigmentService) {
        this.teamService = teamService;
        this.demandService = demandService;
        this.hospitalsAssigmentService = hospitalsAssigmentService;
    }

    @GetMapping("/{demandId}")
    public TeamResponse getTeam(@PathVariable int demandId) {
        return teamService.getTeam(demandId);
    }

    @GetMapping("/{id}/demands")
    public List<ValidatedDemandResponse> getTeamDemands(@PathVariable int id) {
        return demandService.getTeamDemands(id);
    }

    @GetMapping("/{id}/history")
    public List<HospitalsAssignment> getTeamHospitalsAssigments(@PathVariable int id) {
        return hospitalsAssigmentService.getTeamHospitalsAssigments(id);
    }

    @PostMapping("/valid-assignment/{id}")
    public void validAssignment(@PathVariable int id) {
        teamService.validAssignment(id);
    }

    // assign hospital
    @PostMapping("/{id}/assign-hospital")
    public HospitalResponse assignHospital(@RequestBody AssignHospitalRequest assignHospitalRequest,
    @PathVariable("id") int teamId) {
        System.out.println("\n\nassignHospital Code: " + assignHospitalRequest.getCode()  + "\n\n");
        return teamService.assignHospital(assignHospitalRequest, teamId);
    }
    
}
