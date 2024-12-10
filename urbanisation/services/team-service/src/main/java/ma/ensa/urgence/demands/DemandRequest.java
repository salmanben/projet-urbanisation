package ma.ensa.urgence.demands;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



public class DemandRequest {

    private int id;
    private String ref;
    private String cin;
    private long latitude;
    private long longitude;
    private String severityLevel;
    private String description;
    private String status;
    private int categoryId;
    private LocalDateTime createdAt;

    // getters setters

    public DemandRequest() {
    }

    public DemandRequest(int id, String ref, String cin, long latitude, long longitude, String severityLevel, String description, String status, int categoryId, LocalDateTime createdAt) {
        this.id = id;
        this.ref = ref;
        this.cin = cin;
        this.latitude = latitude;
        this.longitude = longitude;
        this.severityLevel = severityLevel;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return this.id;
    }

    public String getRef() {
        return this.ref;
    }

    public String getCin() {
        return this.cin;
    }

    public long getLatitude() {
        return this.latitude;
    }

    public long getLongitude() {
        return this.longitude;
    }

    public String getSeverityLevel() {
        return this.severityLevel;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.status;
    }

    public int getCategoryId() {

        return this.categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    

}
