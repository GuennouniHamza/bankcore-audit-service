package ma.bankcore.audit_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_audit_log")
public class AuditLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//BDD qui génére l'ID
	private Long Id;
	
	@Column(nullable=false)
	private String typeOperation;
	
	@Column(nullable=false)
	private String serviceSource;
	
	private String objetId;// ID de l'objet concerné
	
	@Column(length = 2000)
	private String details;
	

    @Enumerated(EnumType.STRING)
    private StatutAudit statut; // SUCCES, ECHEC

    @Column(updatable = false)
    private LocalDateTime dateOperation;

    @PrePersist
    public void prePersist() {
        this.dateOperation = LocalDateTime.now();
        if (this.statut == null) {
            this.statut = StatutAudit.SUCCES;
        }
    }

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}

	public String getObjetId() {
		return objetId;
	}

	public void setObjetId(String objetId) {
		this.objetId = objetId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public StatutAudit getStatut() {
		return statut;
	}

	public void setStatut(StatutAudit statut) {
		this.statut = statut;
	}

	public LocalDateTime getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}
    
    
}
