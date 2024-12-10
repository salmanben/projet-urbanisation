package ma.ensa.urgence.hospitals;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDto {
    private int id;
    private String code;
    private String description;
}
