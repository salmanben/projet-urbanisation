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
import ma.ensa.urgence.demands.DemandService;
import ma.ensa.urgence.demands.HospitalDemand;

@Service
public class HospitalsAssignmentService {

    private final HospitalsAssignmentDao hospitalsAssignmentDao;
    private final RestTemplate restTemplate;
    private final DemandService demandService;
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

    public HospitalsAssignmentService(HospitalsAssignmentDao hospitalsAssignmentDao,
    DemandService demandService
    , RestTemplate restTemplate) {
        this.hospitalsAssignmentDao = hospitalsAssignmentDao;
        this.restTemplate = restTemplate;
        this.demandService = demandService;

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
            hospitalDemand.setId(hospitalsAssignment.getId());
            DemandRequest demandRequest = new DemandRequest();
            Demand demand = hospitalsAssignment.getDemand();
            demandRequest.setId(demand.getId());
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

    public void handleDemand(HandleDemandRequest handleDemandRequest){
        String status = handleDemandRequest.getStatus();
        int id = handleDemandRequest.getId();
        HospitalsAssignment hospitalsAssignment = hospitalsAssignmentDao.findById(id).get();
        if(!status.equals("ACCEPTÉ")){
           assignHospital(hospitalsAssignment.getHospitalId(), hospitalsAssignment.getCode(),
            hospitalsAssignment.getDemand(), hospitalsAssignment);
        }
        else{
            hospitalsAssignment.setStatus(status);
            hospitalsAssignmentDao.save(hospitalsAssignment);

        }
    }
    
    public void assignHospital(int hospitalId, String code, Demand demand,
     HospitalsAssignment hospitalsAssignment) {
        List<HospitalResponse> hospitals = restTemplate.exchange(
                hospitalServiceUrl + "/code/" + code,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HospitalResponse>>() {
                }).getBody();



        double demandLatitude = demand.getLatitude();
        double demandLongitude = demand.getLongitude();

        // Trouver l'hôpital le plus proche
        HospitalResponse nearestHospital = hospitals.stream()
                .filter(hospital-> hospital.getId() != hospitalId)
                .min((h1, h2) -> Double.compare(
                        calculateDistance(demandLatitude, demandLongitude, h1.getLatitude(), h1.getLongitude()),
                        calculateDistance(demandLatitude, demandLongitude, h2.getLatitude(), h2.getLongitude())))
                .orElseThrow(() -> new RuntimeException("Aucun hôpital trouvé après comparaison des distances."));
        System.out.println("\n\n\n222222\n\n");

        hospitalsAssignment.setHospitalId(nearestHospital.getId());
        storeHospitalAssignment(hospitalsAssignment);

    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
