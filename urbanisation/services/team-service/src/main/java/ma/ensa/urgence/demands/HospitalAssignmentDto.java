package ma.ensa.urgence.demands;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.urgence.hospitals.Hospital;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAssignmentDto {
    private Hospital hospital;
    @JsonProperty("hospital_assignment_date")
    private LocalDateTime createdAt;
    @JsonProperty("hospital_assignment_status")
    private String status;
    private String code;

}
