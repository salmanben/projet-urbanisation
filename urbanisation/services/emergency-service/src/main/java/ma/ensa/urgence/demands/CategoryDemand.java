package ma.ensa.urgence.demands;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CategoryDemand {
    private String name;
    private String description;
}
