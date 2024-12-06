package ma.ensa.urgence.citizens;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String cin;
    private String name;
    private String address;

    @Column(name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "citizen_diseases",
            joinColumns = @JoinColumn(name = "citizen_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private List<Disease> diseases;


}
