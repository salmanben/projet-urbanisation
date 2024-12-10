package ma.ensa.urgence.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ma.ensa.urgence.citizens.Citizen;
import ma.ensa.urgence.demands.DemandRequest;

@Service
public class CitizenProducer {

    private final KafkaTemplate<String, DemandRequest> kafkaTemplate;

    public CitizenProducer(KafkaTemplate<String, DemandRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCitizenDemand(DemandRequest demandRequest) {
      //  kafkaTemplate.send("citizen-topic", demandRequest);
    }
    
}
