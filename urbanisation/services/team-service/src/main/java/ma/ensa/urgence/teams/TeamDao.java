package ma.ensa.urgence.teams;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team, Integer> {
    List<Team> findByAvailabilityAndSeverityLevel(boolean available, String severityLevel);
    // find by userId
    Team findByUserId(int userId);
    
}

