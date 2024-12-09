package ma.ensa.urgence.teams;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.urgence.demands.Demand;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teams_assignments")
public class TeamsAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "demand_id")
    private Demand demand;
    @JsonProperty("team_id")
    private int teamId;
    private String status;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
