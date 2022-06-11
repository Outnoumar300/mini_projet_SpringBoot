package com.gsnotes.web.models;

import javax.validation.constraints.NotNull;

public class DelibirationModel {
	
	private Long idNiveau;

	private String alias;

	private String titre;
	
	//@NotNull(message = "This field is required")
	private int annee;

	public DelibirationModel() {
	}

	public DelibirationModel(Long idNiveau) {
		this.idNiveau = idNiveau;
	}

	
	public DelibirationModel (Long idNiveau, String alias) {
		this.idNiveau = idNiveau;
		this.alias = alias;
	}
    
	
	public DelibirationModel (Long idNiveau, String alias,String titre) {
		this.idNiveau = idNiveau;
		this.alias = alias;
		this.titre = titre;
	}

	public Long getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(Long idNiveau) {
		this.idNiveau = idNiveau;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	
	
}
