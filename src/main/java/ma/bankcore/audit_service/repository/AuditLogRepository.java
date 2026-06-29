package ma.bankcore.audit_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.bankcore.audit_service.entity.AuditLog;
import ma.bankcore.audit_service.entity.StatutAudit;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {
	
		List<AuditLog> findByTypeOperation(String typeOperation);
		
		// Tous les logs d'un service source
	    List<AuditLog> findByServiceSource(String serviceSource);

	    // Tous les logs entre deux dates
	    List<AuditLog> findByDateOperationBetween(
	        LocalDateTime debut,
	        LocalDateTime fin
	    );

	    // Tous les logs en échec
	    List<AuditLog> findByStatut(StatutAudit statut);
}	
