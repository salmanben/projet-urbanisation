package ma.ensa.urgence.demands;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDemand {
    private int id;
    private DemandRequest demandRequest;
    private CitizenDemand citizenDemand;
    private CategoryDemand categoryDemand;
    private String status;
    private String code;
    private LocalDateTime createdAt;
}
