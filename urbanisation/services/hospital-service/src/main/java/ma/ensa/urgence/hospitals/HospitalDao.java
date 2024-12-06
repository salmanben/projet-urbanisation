package ma.ensa.urgence.hospitals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalDao extends JpaRepository<Hospital, Integer> {
}
