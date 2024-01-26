package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

//Si on renomme l'entité par exemple  Operations  ==> @Entity(name="Operations") 
//Pour les requête JPQL il faut utiliser le nom "Operations" et non "Operation"
//@Entity(name="Operations")  

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPERATION", 
discriminatorType = DiscriminatorType.CHAR)
public class Operation implements Serializable{
		
	private static final long serialVersionUID = 8844424227602745332L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private Long numOperation;
	
	@Version
	private int version;
	
	LocalDate dateOperation;
	
	private double montant;
	
	@ManyToOne
	private Compte compte;
	
	
	public Operation() {
	}


	public Operation(LocalDate dateOperation, double montant, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}


	public Long getNumOperation() {
		return numOperation;
	}


	public void setNumOperation(Long numOperation) {
		this.numOperation = numOperation;
	}


	public LocalDate getDateOperation() {
		return dateOperation;
	}


	public void setDateOperation(LocalDate dateOperation) {
		this.dateOperation = dateOperation;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Compte getCompte() {
		return compte;
	}


	public void setCompte(Compte compte) {
		this.compte = compte;
	}


	@Override
	public String toString() {
		return "Operation [numOperation=" + numOperation + ", dateOperation=" + dateOperation + ", montant=" + montant
				+ ", compte=" + compte + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(dateOperation, montant, numOperation);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operation other = (Operation) obj;
		return Objects.equals(dateOperation, other.dateOperation)
				&& Double.doubleToLongBits(montant) == Double.doubleToLongBits(other.montant)
				&& Objects.equals(numOperation, other.numOperation);
	}
	
	
	
}
