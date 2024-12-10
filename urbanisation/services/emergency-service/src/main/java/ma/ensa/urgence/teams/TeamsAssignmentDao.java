package ma.ensa.urgence.teams;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsAssignmentDao  extends JpaRepository<TeamsAssignment, Integer> {

    List<TeamsAssignment> findByTeamId(int id);
    TeamsAssignment findByDemandId(int id);
    
}
