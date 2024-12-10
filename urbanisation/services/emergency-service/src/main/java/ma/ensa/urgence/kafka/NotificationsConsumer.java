package ma.ensa.urgence.kafka;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ma.ensa.urgence.demands.Demand;
import ma.ensa.urgence.demands.DemandDao;
import ma.ensa.urgence.demands.DemandRequest;

@Service
public class NotificationsConsumer {

    private final DemandDao demandDao;

    public NotificationsConsumer(DemandDao demandDao) {
        this.demandDao = demandDao;
    }

    //@KafkaListener(topics = "citizen-topic")
    public void consumeOrderNotification(DemandRequest demandRequest) {
        Demand demand = new Demand();
        demand.setCin(demandRequest.getCin());
        demand.setLatitude(demandRequest.getLatitude());
        demand.setLongitude(demandRequest.getLongitude());
        demand.setSeverityLevel(demandRequest.getSeverityLevel());
        demand.setCategoryId(demandRequest.getCategoryId());
        demand.setStatus("EN ATTENTE");
        demand.setDescription(demandRequest.getDescription());

        // NOW
        String ref = UUID.randomUUID().toString().substring(0, 13);
        demand.setRef(ref);
        demand.setCreatedAt(java.time.LocalDateTime.now());
        System.out.println("\n\n\nHellloo !!!!!!!!!!!!\n\n");
        demandDao.save(demand);
    }
    
}
