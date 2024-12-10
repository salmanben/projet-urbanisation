package ma.ensa.urgence.hospitals;

import org.springframework.stereotype.Service;

@Service
public class HospitalsAssignmentService {

    private final HospitalsAssignmentDao hospitalsAssignmentDao;

    public HospitalsAssignmentService(HospitalsAssignmentDao hospitalsAssignmentDao) {
        this.hospitalsAssignmentDao = hospitalsAssignmentDao;
    }

    public void storeHospitalAssignment(HospitalsAssignment hospitalsAssignment) {
        hospitalsAssignmentDao.save(hospitalsAssignment);
    }
    
}
