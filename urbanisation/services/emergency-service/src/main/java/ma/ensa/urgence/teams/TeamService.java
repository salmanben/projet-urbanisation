package ma.ensa.urgence.teams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.demands.Demand;
import ma.ensa.urgence.demands.DemandDao;

@Service
public class TeamService {

    private final RestTemplate restTemplate;
    private final DemandDao demandDao;
    private TeamsAssignmentDao teamsAssignmentDao;
    @Value("${spring.application.services.team-service.url}")
    private String teamServiceUrl;

    public TeamService(RestTemplate restTemplate, DemandDao demandDao, TeamsAssignmentDao teamsAssignmentDao) {
        this.restTemplate = restTemplate;
        this.demandDao = demandDao;
        this.teamsAssignmentDao = teamsAssignmentDao;
    }

    public TeamResponse getTeam(int demandId) {
        Demand demand = demandDao.findById(demandId).get();
        demand.setStatus("ACCEPTÉ");
        demandDao.save(demand);
        TeamResponse teamResponse = restTemplate.postForObject(teamServiceUrl + "/assign-team", demand,
                TeamResponse.class);
        TeamsAssignment teamsAssignment = new TeamsAssignment();
        teamsAssignment.setDemand(demand);
        teamsAssignment.setTeamId(teamResponse.getId());
        teamsAssignment.setStatus("EN ATTENTE");
        teamsAssignment.setUpdatedAt(java.time.LocalDateTime.now());
        teamsAssignmentDao.save(teamsAssignment);
        return teamResponse;

    }
}
