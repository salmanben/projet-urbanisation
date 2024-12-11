package ma.ensa.urgence.demands;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class TeamDemand {
    private int id;
    private String name;
    private String phone;
    private String status;
    private LocalDateTime createdAt;
}
