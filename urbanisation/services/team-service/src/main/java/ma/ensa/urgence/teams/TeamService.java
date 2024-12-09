package ma.ensa.urgence.teams;

import org.springframework.stereotype.Service;

import ma.ensa.urgence.demands.Demand;

import java.util.List;

@Service
public class TeamService {

    private final TeamDao teamDao;

    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public List<Team> getTeams() {
        return teamDao.findAll();
    }

    public Team getTeamById(int id) {
        return teamDao.findById(id).orElse(null);
    }

    public Team assignTeam(Demand demand) {
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

}
