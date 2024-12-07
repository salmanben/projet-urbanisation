package ma.ensa.urgence.citizens;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CitizenService {
    
    private final RestTemplate restTemplate;

    @Value("${spring.application.services.citizen-service.url}")
    private String citizenServiceUrl;

    public CitizenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CitizenResponse getCitizen(String cin) {
        return restTemplate.getForObject(citizenServiceUrl + "/cin/" + cin, CitizenResponse.class);
    }
    
}
