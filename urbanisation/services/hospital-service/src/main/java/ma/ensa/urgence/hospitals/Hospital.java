package ma.ensa.urgence.hospitals;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;
    private String city;

    @JsonProperty("user_id")
    private int userId;
    private long latitude;
    private long longitude;
    private boolean avaibility;


    @Column(name = "created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hospitals")
    @JsonBackReference
    private List<Code> codes;


}
