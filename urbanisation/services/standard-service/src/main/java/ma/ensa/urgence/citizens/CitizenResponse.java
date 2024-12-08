package ma.ensa.urgence.citizens;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitizenResponse {

    private String cin;
    private String name;
    private String phone;
    private LocalDate birthdate;
    private List<Disease> diseases;

}
