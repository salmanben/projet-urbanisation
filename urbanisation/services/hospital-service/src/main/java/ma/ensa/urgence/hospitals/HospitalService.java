package ma.ensa.urgence.hospitals;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HospitalService {

    private final HospitalDao hospitalDao;
    private final CodeDao codeDao;

    public Hospital getHospitalById(int id) {
        return hospitalDao.findById(id).orElse(null);
    }

    public List<Hospital> getHospitalsByCode(String code) {
        Code codeEntity = codeDao.findByCode(code);
        if(codeEntity == null) {
            return null;
        }
        return codeEntity.getHospitals();
    }
}
