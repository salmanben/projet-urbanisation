package ma.ensa.urgence.demands;

import java.time.LocalDate;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenDemand {
    private String cin;
    private String name;
    private String phone;
    private LocalDate birthdate;
    List<Disease> diseases;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Disease{
    private String name;
    private String description;
}



