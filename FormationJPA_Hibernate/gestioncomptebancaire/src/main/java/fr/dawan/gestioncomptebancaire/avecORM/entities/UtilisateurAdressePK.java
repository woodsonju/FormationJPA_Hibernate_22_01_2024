package fr.dawan.gestioncomptebancaire.avecORM.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Classe Embeddable contenant les clés primaires Utilisateur et Adresse 
 * 	
 */
@Embeddable
public class UtilisateurAdressePK {
	
	//Les attributs idUtilisateur et idAdresse représentent les composants de la primaire composite
	//optional = false : N'autorise pas de valeur null
	@Basic(optional=false)
	@Column(name="utilisateur_id")
	private int idUtilisateur;
	
	@Basic(optional=false)
	@Column(name="adresse_id")
	private int idAdresse;

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	

}
