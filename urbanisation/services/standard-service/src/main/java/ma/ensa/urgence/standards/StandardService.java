package ma.ensa.urgence.standards;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.teams.TeamResponse;

@Service
public class StandardService {

    private final RestTemplate restTemplate;
    @Value("${spring.application.services.emergency-service.url}")
    private String emergencyServiceUrl;

    public StandardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TeamResponse handleDemand(int demandId) {
        return restTemplate.getForObject(emergencyServiceUrl + "/teams/" + demandId ,TeamResponse.class);
    }
}
