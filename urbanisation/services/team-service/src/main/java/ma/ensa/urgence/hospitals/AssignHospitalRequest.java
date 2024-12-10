package ma.ensa.urgence.hospitals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignHospitalRequest {
    
    private String code;
    private int demandId;

}
