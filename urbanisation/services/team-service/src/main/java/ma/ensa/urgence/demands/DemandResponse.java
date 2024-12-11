package ma.ensa.urgence.demands;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandResponse {
    private int id;
    private String ref;
    private int categoryId;
    private String cin;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String description;
    private String status;
    private LocalDateTime createdAt;

}
