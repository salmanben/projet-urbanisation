package ma.ensa.urgence.standards;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.urgence.categories.CategoryResponse;
import ma.ensa.urgence.citizens.CitizenResponse;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandStandard {
    private int id;
    private String ref;
    private LocalDateTime createdAt;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String description;
    private String status;
    private CategoryResponse category;
    private CitizenResponse citizen;

}
