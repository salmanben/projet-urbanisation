package ma.ensa.urgence.demands;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.teams.TeamsAssignment;
import ma.ensa.urgence.teams.TeamsAssignmentDao;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandService {

    private final DemandDao demandDao;

    //category-service
    @Value("${spring.application.services.category-service.url}")
    private String categoryServiceUrl;
    // team service
    @Value("${spring.application.services.team-service.url}")
    private String teamServiceUrl;
    // citizen service
    @Value("${spring.application.services.citizen-service.url}")
    private String citizenServiceUrl;
    private final RestTemplate restTemplate;
    private final TeamsAssignmentDao teamsAssignmentDao;

    public DemandService(DemandDao demandDao, RestTemplate restTemplate,
                         TeamsAssignmentDao teamsAssignmentDao) {
        this.demandDao = demandDao;
        this.restTemplate = restTemplate;
        this.teamsAssignmentDao = teamsAssignmentDao;
    }

    public List<Demand> getDemands() {
       return demandDao.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Demand> getDemandsByCin(String cin) {
        return demandDao.findByCinOrderByCreatedAtDesc(cin);
        
    }

    public List<ValidatedDemandResponse> getValidatedDemands() {
        List<Demand> demands = demandDao.findByStatusOrderByCreatedAtDesc("ACCEPTÉ");
        List<ValidatedDemandResponse> validatedDemandResponses = new ArrayList<>();
        demands.forEach(demand -> {
            ValidatedDemandResponse validatedDemandResponse = new ValidatedDemandResponse();
            validatedDemandResponse.setCreatedAt(demand.getCreatedAt());
            CategoryDemand category = restTemplate.getForObject(categoryServiceUrl + "/" + demand.getCategoryId(), CategoryDemand.class);
            validatedDemandResponse.setRequest(new Request(demand.getRef(), category));
            CitizenDemand citizen = restTemplate.getForObject(citizenServiceUrl + "/cin/" + demand.getCin(), CitizenDemand.class);
            validatedDemandResponse.setCitoyen(citizen);
            validatedDemandResponse.setSeverityLevel(demand.getSeverityLevel());
            TeamsAssignment teamsAssignment = teamsAssignmentDao.findByDemandId(demand.getId());
            TeamDemand team = restTemplate.getForObject(teamServiceUrl + "/" + teamsAssignment.getTeamId(), TeamDemand.class);
            validatedDemandResponse.setTeam(new TeamDemand(team.getName(), team.getTel()));
            validatedDemandResponse.setStatus(demand.getStatus());
            validatedDemandResponses.add(validatedDemandResponse);
        });
        return validatedDemandResponses;
    }
}
