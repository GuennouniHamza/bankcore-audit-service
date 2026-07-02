package ma.bankcore.audit_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VirementEffectueEvent {
	private Long compteSourceId;
	private Long compteDestId;
    private BigDecimal montant;
    private String ribSource;
    private String ribDest;
    private LocalDateTime date;
    
   // Constructeur vide — obligatoire pour Jackson sinn jackson peut pas créer objet -→
    //new VirementEffectueEvent() ← ERREUR ! 
    //Si tu déclares UN constructeur avec paramètres → Java supprime le constructeur vide automatique !
    
    public VirementEffectueEvent() {}
    
	public Long getCompteSourceId() {
		return compteSourceId;
	}

	public void setCompteSourceId(Long compteSourceId) {
		this.compteSourceId = compteSourceId;
	}

	public Long getCompteDestId() {
		return compteDestId;
	}

	public void setCompteDestId(Long compteDestId) {
		this.compteDestId = compteDestId;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getRibSource() {
		return ribSource;
	}

	public void setRibSource(String ribSource) {
		this.ribSource = ribSource;
	}

	public String getRibDest() {
		return ribDest;
	}

	public void setRibDest(String ribDest) {
		this.ribDest = ribDest;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
}
