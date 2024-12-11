package ma.ensa.urgence.demands;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedDemandResponse {
    private int id;
    private LocalDateTime createdAt;
    private String ref;
    private String severityLevel;
    private String status;
    private CategoryDemand category;
    private CitizenDemand citoyen;
    private TeamDemand team;
}


