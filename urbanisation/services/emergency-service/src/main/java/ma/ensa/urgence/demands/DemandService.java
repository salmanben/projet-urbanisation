package ma.ensa.urgence.demands;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {

    private final DemandDao demandDao;

    public DemandService(DemandDao demandDao) {
        this.demandDao = demandDao;
    }

    public List<Demand> getDemands() {
        return demandDao.findAll();
    }

    public List<Demand> getDemandsByCin(String cin) {
        return demandDao.findByCinOrderByCreatedAt(cin);
    }
}
