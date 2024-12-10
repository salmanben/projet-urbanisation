package ma.ensa.urgence.hospitals;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponse {

    private int id;
    private String address;
    private String city;
    private int userId;
    private long latitude;
    private long longitude;
    private String name;
}
