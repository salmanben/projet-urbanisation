package ma.ensa.urgence.demands;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemandService {
    
    private final RestTemplate restTemplate;
    @Value("${spring.application.services.emergency-service.url}")
    private  String emergencyServiceUrl;

    public DemandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void storeDemand(DemandRequest demandRequest){
        restTemplate.postForObject(emergencyServiceUrl + "/demands", demandRequest, Object.class);
    }
    public List<Object> getDemands(String cin){
        return restTemplate.getForObject(emergencyServiceUrl + "/demands/" + cin, List.class);
    }
}
