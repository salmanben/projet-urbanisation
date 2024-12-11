package ma.ensa.urgence.hospitals;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.urgence.demands.Demand;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hospitals_assignments")
public class HospitalsAssignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;
    private int hospitalId;
    private int teamId;
    private String code;
    private String status;
    private LocalDateTime createdAt;

}
