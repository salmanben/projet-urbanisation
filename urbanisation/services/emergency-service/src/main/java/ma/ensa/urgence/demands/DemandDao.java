package ma.ensa.urgence.demands;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DemandDao extends JpaRepository<Demand, Integer> {

    public List<Demand> findByCinOrderByCreatedAtDesc(String cin);
    List<Demand> findByStatusOrderByCreatedAtDesc(String status);

    public List<Demand> findAll(Sort sort);
}
