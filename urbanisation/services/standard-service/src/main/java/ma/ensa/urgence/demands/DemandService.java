package ma.ensa.urgence.demands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DemandService {

    private final RestTemplate restTemplate;
    @Value("${spring.application.services.emergency-service.url}")
    private String emergencyServiceUrl;

    public DemandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DemandResponse> getDemands() {
        ResponseEntity<List<DemandResponse>> response = restTemplate.exchange(
                emergencyServiceUrl + "/demands",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DemandResponse>>() {
                });
        return response.getBody();

    }

    public List<Object> getValidatedDemands() {
        return restTemplate.getForObject(emergencyServiceUrl + "/demands/validated-demands", List.class);
    }
    
}
