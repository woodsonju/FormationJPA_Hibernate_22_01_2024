package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="utilisateur_adresse")
public class UtilisateurAdresse implements Serializable{
	

	private static final long serialVersionUID = 6365373686378208142L;

	/*
	 * L'utilisation de @EmbeddedId  indique que vous avez une clé primaire 
	 * composite pour l'entité UtilisateurAdresse. 
	 * Cela signifie que la clé primaire de UtilisateurAdresse 
	 * est constituée de plusieurs colonnes provenant de l'objet UtilisateurAdressePK.
	 */
	@EmbeddedId
	UtilisateurAdressePK utilisateurAdressePK;
	
	private LocalDate debut;
	
	private LocalDate fin;
	
	private boolean principal; //Si c'est l'adresse principal ou sécondaire
	
	/**
	 * Les objets ne sont pas inserables ni modifiables, car ces données font partie 
	 * de la clé étrangère
	 */
	@JoinColumn(name = "adresse_id", insertable = false, updatable = false)
	@ManyToOne
	private Adresse adresse; 
	
	@JoinColumn(name="utilisateur_id", insertable = false, updatable = false)
	@ManyToOne
	private Utilisateur utilisateur;
	

	public UtilisateurAdresse() {
		// TODO Auto-generated constructor stub
	}


	public UtilisateurAdressePK getUtilisateurAdressePK() {
		return utilisateurAdressePK;
	}


	public void setUtilisateurAdressePK(UtilisateurAdressePK utilisateurAdressePK) {
		this.utilisateurAdressePK = utilisateurAdressePK;
	}


	public LocalDate getDebut() {
		return debut;
	}


	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}


	public LocalDate getFin() {
		return fin;
	}


	public void setFin(LocalDate fin) {
		this.fin = fin;
	}


	public boolean isPrincipal() {
		return principal;
	}


	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	@Override
	public String toString() {
		return "UtilisateurAdresse [utilisateurAdressePK=" + utilisateurAdressePK + ", debut=" + debut + ", fin=" + fin
				+ ", principal=" + principal + ", adresse=" + adresse + ", utilisateur=" + utilisateur + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(adresse, debut, fin, principal, utilisateur);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtilisateurAdresse other = (UtilisateurAdresse) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(debut, other.debut)
				&& Objects.equals(fin, other.fin) && principal == other.principal
				&& Objects.equals(utilisateur, other.utilisateur);
	}
	
	
	

}
