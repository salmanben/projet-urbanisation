package ma.ensa.urgence.hospitals;

import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Code;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    private String address;
    private String city;
    private long latitude;
    private long longitude;
    private String name;

}
