package ma.ensa.urgence.demands;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "demands")
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String ref;

    private String cin;

    private String city;

    private String address;

    private long latitude;

    private long longitude;

    @Column(name = "severity_level")
    private String severityLevel;

    @Column(columnDefinition = "ENUM('EN ATTENTE', 'ACCEPTÉ')")
    private String status;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;




}