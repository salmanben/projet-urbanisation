package ma.ensa.urgence.citizens;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseDao extends JpaRepository<Disease, Integer> {
}
