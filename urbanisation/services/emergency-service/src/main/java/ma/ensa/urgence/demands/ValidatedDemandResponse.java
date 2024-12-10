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
    private Request request;
    private String severityLevel;
    private CitizenDemand citoyen;
    private TeamDemand team;
    private String status;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Request {
    private String ref;
    private CategoryDemand category;
}


