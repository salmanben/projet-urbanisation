package ma.ensa.urgence.citizens;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenDao extends JpaRepository<Citizen, Integer> {
    Citizen findByCin(String cin);
}
