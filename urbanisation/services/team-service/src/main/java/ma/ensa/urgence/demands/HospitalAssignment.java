package ma.ensa.urgence.demands;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAssignment {
    private int id;
    private DemandResponse demand;
    private int hospitalId;
    private String code;
    private LocalDateTime createdAt;
    private String status;

}
