package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "t_bankAccount")
public class Compte implements Serializable{

	private static final long serialVersionUID = -1348168111892974333L;

	@Id
	private String numCompte;
	
	@Version
	private int version;
	
	private LocalDate dateCreation; 
	
	private double solde;
	
	public Compte() {
	}

	public Compte(String numCompte, int version, LocalDate dateCreation, double solde) {
		super();
		this.numCompte = numCompte;
		this.version = version;
		this.dateCreation = dateCreation;
		this.solde = solde;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public int getVersion() {
		return version;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", version=" + version + ", dateCreation=" + dateCreation + ", solde="
				+ solde + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateCreation, numCompte, solde);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(dateCreation, other.dateCreation) && Objects.equals(numCompte, other.numCompte)
				&& Double.doubleToLongBits(solde) == Double.doubleToLongBits(other.solde);
	}
	
	
	
	
}


