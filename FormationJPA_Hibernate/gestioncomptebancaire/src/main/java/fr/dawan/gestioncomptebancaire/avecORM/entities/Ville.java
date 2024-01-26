package fr.dawan.gestioncomptebancaire.avecORM.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Ville implements Serializable{

	private static final long serialVersionUID = 8890241354266396325L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private boolean capital;
	
	private String code_postale;
	
	private String nom;
	
	private String pays;
	
	@Version
	private int version;
	
	public Ville() {
		// TODO Auto-generated constructor stub
	}

	public Ville(boolean capital, String code_postale, String nom, String pays) {
		super();
		this.capital = capital;
		this.code_postale = code_postale;
		this.nom = nom;
		this.pays = pays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getCode_postale() {
		return code_postale;
	}

	public void setCode_postale(String code_postale) {
		this.code_postale = code_postale;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capital, code_postale, id, nom, pays);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		return capital == other.capital && Objects.equals(code_postale, other.code_postale)
				&& Objects.equals(id, other.id) && Objects.equals(nom, other.nom) && Objects.equals(pays, other.pays);
	}
	
	
	
	

}
