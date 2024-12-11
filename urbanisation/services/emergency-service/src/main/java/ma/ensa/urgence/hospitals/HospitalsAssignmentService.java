package ma.ensa.urgence.hospitals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.demands.CategoryDemand;
import ma.ensa.urgence.demands.CitizenDemand;
import ma.ensa.urgence.demands.Demand;
import ma.ensa.urgence.demands.DemandRequest;
import ma.ensa.urgence.demands.HospitalDemand;

@Service
public class HospitalsAssignmentService {

    private final HospitalsAssignmentDao hospitalsAssignmentDao;
    private final RestTemplate restTemplate;
    @Value("${spring.application.services.team-service.url}")
    private String teamServiceUrl;
    @Value("${spring.application.services.hospital-service.url}")
    private String hospitalServiceUrl;
    // category service url
    @Value("${spring.application.services.category-service.url}")
    private String categoryServiceUrl;
    // citizen service url
    @Value("${spring.application.services.citizen-service.url}")
    private String citizenServiceUrl;

    public HospitalsAssignmentService(HospitalsAssignmentDao hospitalsAssignmentDao, RestTemplate restTemplate) {
        this.hospitalsAssignmentDao = hospitalsAssignmentDao;
        this.restTemplate = restTemplate;
    }

    public void storeHospitalAssignment(HospitalsAssignment hospitalsAssignment) {
        hospitalsAssignmentDao.save(hospitalsAssignment);
    }

    public List<HospitalsAssignment> getTeamHospitalsAssigments(int id) {
        return hospitalsAssignmentDao.findByTeamId(id);
    }

    public List<HospitalDemand> getHospitalDemands(int id) {
        List<HospitalsAssignment> hospitalsAssignments
         = hospitalsAssignmentDao.findByHospitalId(id);
        List<HospitalDemand> hospitalDemands = new ArrayList<>();
        hospitalsAssignments.forEach(hospitalsAssignment -> {
            HospitalDemand hospitalDemand = new HospitalDemand();
            DemandRequest demandRequest = new DemandRequest();
            Demand demand = hospitalsAssignment.getDemand();
            demandRequest.setCin(demand.getCin());
            demandRequest.setLatitude(demand.getLatitude());
            demandRequest.setLongitude(demand.getLongitude());
            demandRequest.setSeverityLevel(demand.getSeverityLevel());
            demandRequest.setDescription(demand.getDescription());
            demandRequest.setCategoryId(demand.getCategoryId());
            demandRequest.setCreatedAt(demand.getCreatedAt());
            hospitalDemand.setDemandRequest(demandRequest);
            CategoryDemand categoryDemand = restTemplate.exchange(categoryServiceUrl + "/" + demand.getCategoryId(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<CategoryDemand>() {
                    }).getBody();
            hospitalDemand.setCategoryDemand(categoryDemand);
            CitizenDemand citizenDemand = restTemplate.exchange(citizenServiceUrl + "/cin/" + demand.getCin(),
                    HttpMethod.GET, null, new ParameterizedTypeReference<CitizenDemand>() {
                    }).getBody();
            hospitalDemand.setCitizenDemand(citizenDemand);
            hospitalDemand.setStatus(hospitalsAssignment.getStatus());
            hospitalDemand.setCode(hospitalsAssignment.getCode());
            hospitalDemand.setCreatedAt(hospitalsAssignment.getCreatedAt());
            hospitalDemands.add(hospitalDemand);

        });

        return hospitalDemands;
    }
}
