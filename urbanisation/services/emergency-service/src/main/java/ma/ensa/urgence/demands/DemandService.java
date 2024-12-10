package ma.ensa.urgence.demands;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.teams.TeamsAssignment;
import ma.ensa.urgence.teams.TeamsAssignmentDao;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DemandService {

    private final DemandDao demandDao;

    // category-service
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

    public void storeDemand(DemandRequest demandRequest) {
        Demand demand = new Demand();
        demand.setCin(demandRequest.getCin());
        demand.setLatitude(demandRequest.getLatitude());
        demand.setLongitude(demandRequest.getLongitude());
        demand.setSeverityLevel(demandRequest.getSeverityLevel());
        demand.setCategoryId(demandRequest.getCategoryId());
        demand.setStatus("EN ATTENTE");
        demand.setDescription(demandRequest.getDescription());

        // NOW
        String ref = UUID.randomUUID().toString().substring(0, 13);
        demand.setRef(ref);
        demand.setCreatedAt(java.time.LocalDateTime.now());
        System.out.println("\n\n\nHellloo !!!!!!!!!!!!\n\n");
        demandDao.save(demand);
    }

    public List<Demand> getDemands() {
        return demandDao.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<DemandsCitizen> getDemandsByCin(String cin) {
        List<DemandsCitizen> demandsCitizen = new ArrayList<>();
        demandsCitizen = demandDao.findByCinOrderByCreatedAtDesc(cin).stream()
                .map(demand -> {
                    DemandsCitizen demandsCitizen1 = new DemandsCitizen();
                    demandsCitizen1.setId(demand.getId());
                    demandsCitizen1.setRef(demand.getRef());
                    demandsCitizen1.setDescription(demand.getDescription());
                    demandsCitizen1.setLatitude(demand.getLatitude());
                    demandsCitizen1.setLongitude(demand.getLongitude());
                    demandsCitizen1.setSeverityLevel(demand.getSeverityLevel());
                    demandsCitizen1.setStatus(demand.getStatus());
                    CategoryDemand category = restTemplate
                            .getForObject(categoryServiceUrl + "/" + demand.getCategoryId(), CategoryDemand.class);
                    demandsCitizen1.setCategory(category);
                    demandsCitizen1.setCreatedAt(demand.getCreatedAt());
                    return demandsCitizen1;
                }).toList();
        return demandsCitizen;

    }

    public List<ValidatedDemandResponse> getValidatedDemands() {
        List<Demand> demands = demandDao.findByStatusOrderByCreatedAtDesc("ACCEPTÉ");
        List<ValidatedDemandResponse> validatedDemandResponses = new ArrayList<>();
        demands.forEach(demand -> {
            ValidatedDemandResponse validatedDemandResponse = new ValidatedDemandResponse();
            validatedDemandResponse.setCreatedAt(demand.getCreatedAt());
            CategoryDemand category = restTemplate.getForObject(categoryServiceUrl + "/" + demand.getCategoryId(),
                    CategoryDemand.class);
            validatedDemandResponse.setRequest(new Request(demand.getRef(), category));
            CitizenDemand citizen = restTemplate.getForObject(citizenServiceUrl + "/cin/" + demand.getCin(),
                    CitizenDemand.class);
            validatedDemandResponse.setCitoyen(citizen);
            validatedDemandResponse.setSeverityLevel(demand.getSeverityLevel());
            TeamsAssignment teamsAssignment = teamsAssignmentDao.findByDemandId(demand.getId());
            TeamDemand team = restTemplate.getForObject(teamServiceUrl + "/" + teamsAssignment.getTeamId(),
                    TeamDemand.class);
            System.out.println("\n\n\nTeam: " + team + "\n\n");
            validatedDemandResponse.setTeam(new TeamDemand(team.getName(), team.getPhone()));
            validatedDemandResponse.setStatus(demand.getStatus());
            validatedDemandResponses.add(validatedDemandResponse);
        });
        return validatedDemandResponses;
    }
}
