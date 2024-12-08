package ma.ensa.urgence.demands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandRequest {

    private String cin;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String city;
    private String address;
    private int categoryId;

}
