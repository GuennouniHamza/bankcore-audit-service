package ma.bankcore.audit_service.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ma.bankcore.audit_service.dto.ClientCreeEvent;
import ma.bankcore.audit_service.dto.CompteBloqueEvent;
import ma.bankcore.audit_service.dto.VirementEffectueEvent;
import ma.bankcore.audit_service.entity.AuditLog;
import ma.bankcore.audit_service.entity.StatutAudit;
import ma.bankcore.audit_service.repository.AuditLogRepository;

@Service
public class AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditService.class);

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Async("auditTaskExecutor")
    public CompletableFuture<Void> tracerVirement(VirementEffectueEvent event) {
        log.debug("Traçage virement : {} → {}", event.getRibSource(), event.getRibDest());

        AuditLog auditLog = new AuditLog();
        auditLog.setTypeOperation("VIREMENT");
        auditLog.setServiceSource("compte-service");
        auditLog.setObjetId(String.valueOf(event.getCompteSourceId()));
        auditLog.setDetails(
            "Virement de " + event.getMontant() + " MAD" +
            " de " + event.getRibSource() +
            " vers " + event.getRibDest()
        );
        auditLog.setStatut(StatutAudit.SUCCES);

        auditLogRepository.save(auditLog);
        log.info("AuditLog virement sauvegardé ✅");

        return CompletableFuture.completedFuture(null);
    }

    @Async("auditTaskExecutor")
    public CompletableFuture<Void> tracerCreationClient(ClientCreeEvent event) {
        log.debug("Traçage création client : {}", event.getEmail());

        AuditLog auditLog = new AuditLog();
        auditLog.setTypeOperation("CREATION_CLIENT");
        auditLog.setServiceSource("client-service");
        auditLog.setObjetId(String.valueOf(event.getClientId()));
        auditLog.setDetails(
            "Client créé : " + event.getNom() +
            " (" + event.getEmail() + ")"
        );
        auditLog.setStatut(StatutAudit.SUCCES);

        auditLogRepository.save(auditLog);
        log.info("AuditLog création client sauvegardé ✅");

        return CompletableFuture.completedFuture(null);
    }

    @Async("auditTaskExecutor")
    public CompletableFuture<Void> tracerBlocageCompte(CompteBloqueEvent event) {
        log.debug("Traçage blocage compte : {}", event.getRib());

        AuditLog auditLog = new AuditLog();
        auditLog.setTypeOperation("BLOCAGE_COMPTE");
        auditLog.setServiceSource("compte-service");
        auditLog.setObjetId(String.valueOf(event.getCompteId()));
        auditLog.setDetails("Compte bloqué : " + event.getRib());
        auditLog.setStatut(StatutAudit.SUCCES);

        auditLogRepository.save(auditLog);
        log.info("AuditLog blocage compte sauvegardé ✅");

        return CompletableFuture.completedFuture(null);
    }
}