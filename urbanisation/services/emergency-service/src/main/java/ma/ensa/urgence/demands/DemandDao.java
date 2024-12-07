package ma.ensa.urgence.demands;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandDao extends JpaRepository<Demand, Integer> {

    public List<Demand> findByCinOrderByCreatedAt(String cin);
}
