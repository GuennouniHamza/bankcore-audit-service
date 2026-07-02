package ma.bankcore.audit_service.consumer;

import ma.bankcore.audit_service.dto.ClientCreeEvent;
import ma.bankcore.audit_service.dto.CompteBloqueEvent;
import ma.bankcore.audit_service.dto.VirementEffectueEvent;
import ma.bankcore.audit_service.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(AuditEventConsumer.class);

    private final AuditService auditService;

    public AuditEventConsumer(AuditService auditService) {
        this.auditService = auditService;
    }

    @KafkaListener(
        topics = "virement-events",
        groupId = "audit-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleVirement(VirementEffectueEvent event) {
        log.info("Événement virement reçu depuis Kafka : {} → {}",
            event.getRibSource(), event.getRibDest());
        auditService.tracerVirement(event); // @Async — non bloquant
    }

    @KafkaListener(
        topics = "client-events",
        groupId = "audit-group",
        containerFactory = "clientKafkaListenerContainerFactory"
    )
    public void handleClientCree(ClientCreeEvent event) {
        log.info("Événement création client reçu : {}", event.getEmail());
        auditService.tracerCreationClient(event);
    }

    @KafkaListener(
        topics = "compte-events",
        groupId = "audit-group",
        containerFactory = "compteKafkaListenerContainerFactory"
    )
    public void handleCompteBloquer(CompteBloqueEvent event) {
        log.info("Événement blocage compte reçu : {}", event.getRib());
        auditService.tracerBlocageCompte(event);
    }
}