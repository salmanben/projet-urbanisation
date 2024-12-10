package ma.ensa.urgence.demands;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandsCitizen {

    private int id;
    private String ref;
    private String description;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String status;
    private CategoryDemand category;
    private LocalDateTime createdAt;
}
