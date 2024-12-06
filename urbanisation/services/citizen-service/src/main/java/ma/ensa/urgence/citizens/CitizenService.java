package ma.ensa.urgence.citizens;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CitizenService {

    private final CitizenDao citizenDao;

    public Citizen getCitizenByCIN(String cin) {
        return citizenDao.findByCin(cin);
    }

    public Citizen getCitizenById(int id) {
        return citizenDao.findById(id).orElse(null);
    }
}
