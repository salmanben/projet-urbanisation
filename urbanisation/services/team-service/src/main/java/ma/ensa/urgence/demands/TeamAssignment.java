package ma.ensa.urgence.demands;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamAssignment {

    private DemandResponse demand;
    private String status;
    private LocalDateTime createdAt;
    
}
