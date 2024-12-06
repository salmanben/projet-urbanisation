package ma.ensa.urgence.hospitals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "codes")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "code_hospitals",
            joinColumns = @JoinColumn(name = "code_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    @JsonManagedReference
    private List<Hospital> hospitals;

}
