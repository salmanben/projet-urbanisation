package ma.ensa.urgence.citizens;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disease {

    private int id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    
}
