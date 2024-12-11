package ma.ensa.urgence.demands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse {
    private DemandResponse demand;
    private CategoryDemand category;
    private CitizenDemand citoyen;
    private HospitalAssignmentDto hospital;

}
