package ma.ensa.urgence.demands;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {

    private final DemandDao demandDao;

    public DemandService(DemandDao demandDao) {
        this.demandDao = demandDao;
    }

    public List<Demand> getDemands() {
       return demandDao.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Demand> getDemandsByCin(String cin) {
        return demandDao.findByCinOrderByCreatedAtDesc(cin);
    }
}
