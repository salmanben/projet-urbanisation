package ma.ensa.urgence.demands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CitizenDemand {
    private String cin;
    private String name;
    private String tel;
}

