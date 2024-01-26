package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private double decouvert;
	
	public CompteCourant() {
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String numCompte, LocalDate dateCreation, double solde, Utilisateur client, double decouvert) {
		super(numCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	
	
}
