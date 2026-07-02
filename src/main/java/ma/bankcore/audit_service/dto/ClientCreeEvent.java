package ma.bankcore.audit_service.dto;

import java.time.LocalDateTime;

public class ClientCreeEvent {

    private Long clientId;
    private String nom;
    private String email;
    private LocalDateTime date;

    public ClientCreeEvent() {}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

    
}