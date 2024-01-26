package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
	
	private double taux; 
	
	public CompteEpargne() {
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String numCompte, LocalDate dateCreation, double solde, Utilisateur client, double taux) {
		super(numCompte, dateCreation, solde, client);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	
	

}
