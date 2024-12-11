package ma.ensa.urgence.hospitals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandleDemandRequest {
    private int id;
    private String status;

}
