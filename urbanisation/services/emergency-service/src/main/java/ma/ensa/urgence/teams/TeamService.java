package ma.ensa.urgence.teams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.demands.Demand;
import ma.ensa.urgence.demands.DemandDao;
import ma.ensa.urgence.demands.DemandService;


@Service
public class TeamService {
    
    private final RestTemplate restTemplate;
    private final DemandDao demandDao;
    @Value("${spring.application.services.team-service.url}")
    private String teamServiceUrl;

    public TeamService(RestTemplate restTemplate, DemandDao demandDao) {
        this.restTemplate = restTemplate;
        this.demandDao = demandDao;
    }

    public TeamResponse getTeam(int demandId) {
        Demand demand = demandDao.findById(demandId).get();
        demand.setStatus("ACCEPTÉ");
        demandDao.save(demand);
        TeamResponse teamResponse = restTemplate.postForObject(teamServiceUrl + "/assign-team", demand, TeamResponse.class);
        return teamResponse;
    }
}
