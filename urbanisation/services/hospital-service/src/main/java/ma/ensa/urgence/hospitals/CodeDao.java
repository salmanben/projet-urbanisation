package ma.ensa.urgence.hospitals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDao extends JpaRepository<Code, Integer> {
    Code findByCode(String code);
}
