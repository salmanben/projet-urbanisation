package ma.ensa.urgence.demands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandResponse {

    private int id;
    private String ref;
    private String cin;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String city;
    private String description;
    private int categoryId;
    private LocalDateTime createdAt;

}
