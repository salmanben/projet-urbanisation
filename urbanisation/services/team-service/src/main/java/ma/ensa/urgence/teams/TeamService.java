package ma.ensa.urgence.teams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.ensa.urgence.demands.DemandRequest;
import ma.ensa.urgence.hospitals.AssignHospitalRequest;

import java.util.List;

@Service
public class TeamService {

    private final TeamDao teamDao;
    private final RestTemplate restTemplate;
    @Value("${spring.application.services.emergency-service.url}")
    private String emergencyServiceUrl;
    @Value("${spring.application.services.hospital-service.url}")
    private String hospitalServiceUrl;

    public TeamService(TeamDao teamDao, RestTemplate restTemplate) {
        this.teamDao = teamDao;
        this.restTemplate = restTemplate;
    }

    public List<Team> getTeams() {
        return teamDao.findAll();
    }

    public Team getTeamById(int id) {
        return teamDao.findById(id).orElse(null);
    }

    public Team getTeamByUserId(int userId) {
        return teamDao.findByUserId(userId);
    }


    public Team assignTeam(DemandRequest demand) {
        String severityLevel = demand.getSeverityLevel();
        double demandLongitude = demand.getLongitude();
        double demandLatitude = demand.getLatitude();

        List<Team> teams = teamDao.findByAvailabilityAndSeverityLevel(true, severityLevel);

        if (teams.isEmpty()) {
            System.out.println("Aucune équipe disponible pour ce niveau de sévérité.");
            return null;
        }
        Team closestTeam = null;
        double minDistance = Double.MAX_VALUE;

        for (Team team : teams) {
            double teamLongitude = team.getLongitude();
            double teamLatitude = team.getLatitude();

            double distance = calculateDistance(demandLatitude, demandLongitude, teamLatitude, teamLongitude);

            if (distance < minDistance) {
                minDistance = distance;
                closestTeam = team;
            }
        }

        System.out.println("Équipe la plus proche : " + closestTeam.getName() + " avec une distance de " + minDistance);
        return closestTeam;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Rayon de la Terre en kilomètres

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculer la distance
        return EARTH_RADIUS * c;
    }

    public List<Object> getDemands(int userId) {
        Team team = teamDao.findByUserId(userId);
        System.out.println("\n\nTeam: " + team + "\n");
        System.out.println("\n\nUserId: " + userId + "\n");

        List<Object> demands = restTemplate.getForObject(emergencyServiceUrl + "/teams/" + team.getId() + "/demands",
                List.class);

        return demands;
    }

    public List<Object> getCodes() {
        System.out.println("\n\nHospital Service URL: " + hospitalServiceUrl + "\n");
        List<Object> codes = restTemplate.getForObject(hospitalServiceUrl + "/codes", List.class);
        return codes;
    }

    public void validDemand(int id) {
        restTemplate.postForObject(emergencyServiceUrl + "/teams/valid-assignment/" + id, null, Void.class);
    }

    public Object assignHospital(AssignHospitalRequest assignHospitalRequest) {
        return restTemplate.postForObject(emergencyServiceUrl + "/teams/assign-hospital", assignHospitalRequest, Object.class);
    }

}
