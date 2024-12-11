package ma.ensa.urgence.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.demands.Demand;
import ma.ensa.urgence.demands.DemandDao;
import ma.ensa.urgence.demands.ValidatedDemandResponse;
import ma.ensa.urgence.hospitals.AssignHospitalRequest;
import ma.ensa.urgence.hospitals.HospitalResponse;
import ma.ensa.urgence.hospitals.HospitalsAssignment;
import ma.ensa.urgence.hospitals.HospitalsAssignmentService;

@Service
public class TeamService {

    private final RestTemplate restTemplate;
    private final DemandDao demandDao;
    private TeamsAssignmentDao teamsAssignmentDao;
    private final HospitalsAssignmentService hospitalsAssignmentService;
    @Value("${spring.application.services.team-service.url}")
    private String teamServiceUrl;
    @Value("${spring.application.services.hospital-service.url}")
    private String hospitalServiceUrl;
    // category service url
    @Value("${spring.application.services.category-service.url}")
    private String categoryServiceUrl;


    public TeamService(RestTemplate restTemplate, DemandDao demandDao,
            HospitalsAssignmentService hospitalsAssignmentService,
            TeamsAssignmentDao teamsAssignmentDao) {
        this.restTemplate = restTemplate;
        this.demandDao = demandDao;
        this.teamsAssignmentDao = teamsAssignmentDao;
        this.hospitalsAssignmentService = hospitalsAssignmentService;
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
        teamsAssignment.setCreatedAt(java.time.LocalDateTime.now());
        teamsAssignmentDao.save(teamsAssignment);
        return teamResponse;

    }




    public void validAssignment(int id) {
        TeamsAssignment teamsAssignment = teamsAssignmentDao.findByDemandIdOrderByIdDesc(id);
        teamsAssignment.setStatus("ACCEPTÉ");
        teamsAssignmentDao.save(teamsAssignment);
    }

    public HospitalResponse assignHospital(AssignHospitalRequest assignHospitalRequest, int teamId) {
        List<HospitalResponse> hospitals = restTemplate.exchange(
                hospitalServiceUrl + "/code/" + assignHospitalRequest.getCode(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HospitalResponse>>() {
                }).getBody();

        if (hospitals == null || hospitals.isEmpty()) {
            throw new RuntimeException("Aucun hôpital trouvé pour le code fourni : " + assignHospitalRequest.getCode());
        }

        Demand demand = demandDao.findById(assignHospitalRequest.getDemandId())
                .orElseThrow(() -> new RuntimeException(
                        "Demande introuvable pour l'ID : " + assignHospitalRequest.getDemandId()));

        System.out.println("\n\n\n111111\n\n");

        double demandLatitude = demand.getLatitude();
        double demandLongitude = demand.getLongitude();

        // Trouver l'hôpital le plus proche
        HospitalResponse nearestHospital = hospitals.stream()
                .min((h1, h2) -> Double.compare(
                        calculateDistance(demandLatitude, demandLongitude, h1.getLatitude(), h1.getLongitude()),
                        calculateDistance(demandLatitude, demandLongitude, h2.getLatitude(), h2.getLongitude())))
                .orElseThrow(() -> new RuntimeException("Aucun hôpital trouvé après comparaison des distances."));
        System.out.println("\n\n\n222222\n\n");

        HospitalsAssignment hospitalAssignment = new HospitalsAssignment();
        hospitalAssignment.setDemand(demand);
        hospitalAssignment.setCode(assignHospitalRequest.getCode());
        hospitalAssignment.setHospitalId(nearestHospital.getId());
        hospitalAssignment.setCreatedAt(java.time.LocalDateTime.now());
        hospitalAssignment.setStatus("EN ATTENTE");
        hospitalAssignment.setTeamId(teamId);
        hospitalsAssignmentService.storeHospitalAssignment(hospitalAssignment);

        // Préparer la réponse
        HospitalResponse hospitalResponse = new HospitalResponse();
        hospitalResponse.setId(nearestHospital.getId());
        hospitalResponse.setName(nearestHospital.getName());
        hospitalResponse.setLatitude(nearestHospital.getLatitude());
        hospitalResponse.setLongitude(nearestHospital.getLongitude());
        hospitalResponse.setAddress(nearestHospital.getAddress());
        hospitalResponse.setCity(nearestHospital.getCity());
        hospitalResponse.setUserId(nearestHospital.getUserId());

        return hospitalResponse;
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
