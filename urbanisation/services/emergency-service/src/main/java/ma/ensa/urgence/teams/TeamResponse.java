package ma.ensa.urgence.teams;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    private int id;
    private String name;
    private String phone;
    private String address;
    private long latitude;
    private long longitude;
    private String severityLevel;
}
