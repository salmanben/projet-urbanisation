package ma.ensa.urgence.hospitals;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalsAssignmentDao extends JpaRepository<HospitalsAssignment, Integer> {

    List<HospitalsAssignment> findByTeamIdOrderByIdDesc(int teamId);
    List<HospitalsAssignment> findByHospitalIdOrderByIdDesc(int hospitalId);
    
}
