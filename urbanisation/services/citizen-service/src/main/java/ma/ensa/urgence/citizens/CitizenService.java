package ma.ensa.urgence.citizens;

import org.springframework.stereotype.Service;

@Service
public class CitizenService {

    private final CitizenDao citizenDao;
    public CitizenService(CitizenDao citizenDao) {
        this.citizenDao = citizenDao;
    }

    public Citizen getCitizenByCIN(String cin) {
        return citizenDao.findByCin(cin);
    }

    public Citizen getCitizenById(int id) {
        return citizenDao.findById(id).orElse(null);
    }
}
