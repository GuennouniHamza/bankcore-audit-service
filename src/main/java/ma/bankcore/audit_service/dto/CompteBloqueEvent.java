package ma.bankcore.audit_service.dto;

import java.time.LocalDateTime;

public class CompteBloqueEvent {

    private Long compteId;
    private String rib;
    private LocalDateTime date;

    public CompteBloqueEvent() {}

	public Long getCompteId() {
		return compteId;
	}

	public void setCompteId(Long compteId) {
		this.compteId = compteId;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

    
    
}